package br.senac.rj.grupo1.carrinhodecompra.services;

import br.senac.rj.grupo1.carrinhodecompra.dto.EstoqueDTO;
import br.senac.rj.grupo1.carrinhodecompra.dto.ItemCarrinhoDTO;
import br.senac.rj.grupo1.carrinhodecompra.entities.Carrinho;
import br.senac.rj.grupo1.carrinhodecompra.entities.ItemCarrinho;
import br.senac.rj.grupo1.carrinhodecompra.interfacefeign.EstoqueFeignClient;
import br.senac.rj.grupo1.carrinhodecompra.repository.CarrinhoRepository;
import br.senac.rj.grupo1.carrinhodecompra.repository.ItemCarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ItemCarrinhoService {

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    
    @Autowired
    private EstoqueFeignClient estoqueFeignClient;

    @Transactional
    public ItemCarrinho addItemToCart(int cartId, ItemCarrinhoDTO itemCarrinhoDTO) {
        Optional<Carrinho> carrinhoOpt = carrinhoRepository.findById(cartId);

        if (carrinhoOpt.isPresent()) {
            Carrinho carrinho = carrinhoOpt.get();
            ItemCarrinho itemCarrinho = new ItemCarrinho();
            itemCarrinho.setCarrinhoId(carrinho);
            itemCarrinho.setProdutoId(itemCarrinhoDTO.getProdutoId());
            itemCarrinho.setQuantidade(itemCarrinhoDTO.getQuantidade());

            ItemCarrinho savedItem = itemCarrinhoRepository.save(itemCarrinho);
            updateTotalCarrinho(carrinho);

            return savedItem;
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
        Optional<Carrinho> carrinhoOpt = carrinhoRepository.findById(cartId);
        carrinhoOpt.ifPresent(this::updateTotalCarrinho);
    }

    @Transactional
    public void deleteItemFromCart(int cartId, long produtoId) {
        itemCarrinhoRepository.deleteItemFromCart(cartId, produtoId);
        Optional<Carrinho> carrinhoOpt = carrinhoRepository.findById(cartId);
        carrinhoOpt.ifPresent(this::updateTotalCarrinho);
    }

    public Optional<ItemCarrinho> getItemById(int itemId) {
        return itemCarrinhoRepository.findById(itemId);
    }
    
    public Optional<ItemCarrinho> getItemByCartIdAndProductId(int cartId, long produtoId) {
        return itemCarrinhoRepository.findByCartIdAndProductId(cartId, produtoId);
    }
    
    private void updateTotalCarrinho(Carrinho carrinho) {
        List<ItemCarrinho> itens = itemCarrinhoRepository.findItemsByCartId(carrinho.getId());
        BigDecimal total = BigDecimal.ZERO;

        for (ItemCarrinho item : itens) {
            ResponseEntity<EstoqueDTO> response = estoqueFeignClient.findById(item.getProdutoId());
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                BigDecimal precoVenda = response.getBody().getPreco();
                total = total.add(precoVenda.multiply(BigDecimal.valueOf(item.getQuantidade())));
            }
        }

        carrinho.setValorTotal(total);
        carrinhoRepository.save(carrinho);
    }
   
}
