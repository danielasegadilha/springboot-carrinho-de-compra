package br.senac.rj.grupo1.carrinhodecompra.services;

import org.springframework.stereotype.Service;

import com.senac.victor.gerenciamentosalas2.exception.EntidadeNaoEncontradaException;

import br.senac.rj.grupo1.carrinhodecompra.entities.Carrinho;
import br.senac.rj.grupo1.carrinhodecompra.repository.CarrinhoRepository;
import jakarta.transaction.Transactional;

@Service
public class CarrinhoService {
	
	private final CarrinhoRepository carrinhoRepository;
	
	public CarrinhoService(CarrinhoRepository carrinhoRepository) {
		this.carrinhoRepository = carrinhoRepository;
	}
	
	@Transactional
	public Carrinho createCarrinho(Carrinho carrinho) {
		return carrinhoRepository.save(carrinho)
	}
	
	@Transactional Carrinho updateCarrinho(Carrinho carrinho) {
		if (!carrinhoRepository.existsById(carrinho.getId())) {
			throw new EntidadeNaoEncontradaException("Carrinho com ID " + id + " não encontrado para deleção");
		}
		return carrinhoRepository.save(carrinho);
	}
}
