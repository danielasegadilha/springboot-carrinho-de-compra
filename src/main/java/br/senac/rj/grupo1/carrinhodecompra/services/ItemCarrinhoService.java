package br.senac.rj.grupo1.carrinhodecompra.services;

import br.senac.rj.grupo1.carrinhodecompra.dto.ItemCarrinhoDTO;
import br.senac.rj.grupo1.carrinhodecompra.entities.Carrinho;
import br.senac.rj.grupo1.carrinhodecompra.entities.ItemCarrinho;
import br.senac.rj.grupo1.carrinhodecompra.repository.CarrinhoRepository;
import br.senac.rj.grupo1.carrinhodecompra.repository.ItemCarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemCarrinhoService {

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Transactional
    public ItemCarrinho addItemToCart(int cartId, ItemCarrinhoDTO itemCarrinhoDTO) {
        Optional<Carrinho> carrinhoOpt = carrinhoRepository.findById(cartId);

        if (carrinhoOpt.isPresent()) {
            Carrinho carrinho = carrinhoOpt.get();
            ItemCarrinho itemCarrinho = new ItemCarrinho();
            itemCarrinho.setCarrinhoId(carrinho);  // Associar o carrinho
            itemCarrinho.setProdutoId(itemCarrinhoDTO.getProdutoId());
            itemCarrinho.setQuantidade(itemCarrinhoDTO.getQuantidade());

            return itemCarrinhoRepository.save(itemCarrinho);  // O JPA gerenciará o ID automaticamente
        } else {
            throw new IllegalArgumentException("Carrinho não encontrado.");
        }
    }

    public List<ItemCarrinho> getItemsByCartId(int cartId) {
        return itemCarrinhoRepository.findItemsByCartId(cartId);
    }

    @Transactional
    public void updateItemQuantity(int cartId, ItemCarrinhoDTO itemCarrinhoDTO) {
        itemCarrinhoRepository.updateItemQuantity(cartId, itemCarrinhoDTO.getProdutoId(), itemCarrinhoDTO.getQuantidade());
    }

    @Transactional
    public void deleteItemFromCart(int cartId, long produtoId) {
        itemCarrinhoRepository.deleteItemFromCart(cartId, produtoId);
    }

    public Optional<ItemCarrinho> getItemById(int itemId) {
        return itemCarrinhoRepository.findById(itemId);
    }
    
    public Optional<ItemCarrinho> getItemByCartIdAndProductId(int cartId, long produtoId) {
        return itemCarrinhoRepository.findByCartIdAndProductId(cartId, produtoId);
    }
}
