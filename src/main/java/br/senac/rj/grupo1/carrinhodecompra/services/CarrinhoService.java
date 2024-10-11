package br.senac.rj.grupo1.carrinhodecompra.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senac.rj.grupo1.carrinhodecompra.exception.EntidadeNaoEncontradaException;
import br.senac.rj.grupo1.carrinhodecompra.interfacefeign.AcompanhamentoPedidoFeignClient;
import br.senac.rj.grupo1.carrinhodecompra.interfacefeign.EstoqueFeignClient;
import br.senac.rj.grupo1.carrinhodecompra.entities.Carrinho;
import br.senac.rj.grupo1.carrinhodecompra.entities.ItemCarrinho;
import br.senac.rj.grupo1.carrinhodecompra.repository.CarrinhoRepository;
import jakarta.transaction.Transactional;

@Service
public class CarrinhoService {
	@Autowired
	AcompanhamentoPedidoFeignClient acompanhamentoPedidoFeignClient;
	
	@Autowired
	EstoqueFeignClient estoqueFeignClient;
	
	@Autowired
	ItemCarrinhoService itemCarrinhoService;
	
	private final CarrinhoRepository carrinhoRepository;
	
	public CarrinhoService(CarrinhoRepository carrinhoRepository) {
		this.carrinhoRepository = carrinhoRepository;
	}
	
    @Transactional
    public Carrinho createCarrinho(int userId) {
        carrinhoRepository.createCarrinho(userId);
        return carrinhoRepository.findTopByUsuarioIdOrderByIdDesc(userId);
    }
		
	@Transactional
	public void removeCarrinhoById(int id) {
		if (!carrinhoRepository.existsById(id)) {
			throw new EntidadeNaoEncontradaException("Carrinho com ID " + id + " não encontrado para deleção");
		}
		carrinhoRepository.softDeleteCarrinhoById(id);
	}
	
	@Transactional
	public void finalizaCarrinhoById(int id) {
		if (!carrinhoRepository.existsById(id)) {
			throw new EntidadeNaoEncontradaException("Carrinho com ID " + id + " não encontrado para finalização");
		}
		
		Carrinho carrinho = carrinhoRepository.findById(id).orElseThrow(() -> 
        new EntidadeNaoEncontradaException("Carrinho com ID " + id + " não encontrado para finalização"));
		
		long usuarioId = carrinho.getUsuarioId();
		
		carrinhoRepository.FinalizarCarrinhoById(id);
		
		acompanhamentoPedidoFeignClient.createAcompanhamentoPedido(usuarioId, id, "PREPARACAO");
		
		List<ItemCarrinho> itens = itemCarrinhoService.getItemsByCartId(id);
		for (ItemCarrinho item : itens) {
	        long produtoId = item.getProdutoId();
	        int quantidade = item.getQuantidade();
	        estoqueFeignClient.updateEstoqueById(produtoId, quantidade);
	    }
	}
	
	public Carrinho getCarrinhoById(int id) {
		return carrinhoRepository.findByIdAndStatusNot(id, -1)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Carrinho com ID " + id + " não encontrado"));
	}
	
	public List<Carrinho> getCarrinhoFinalizadoByUserId(int UserId) {
		return carrinhoRepository.findCarrinhosFinalizadosByUserId(UserId);
	}
	
	public List<Carrinho> getAllCarrinhos(){
		return carrinhoRepository.findCarrinhosAtivos();
	}
	
}