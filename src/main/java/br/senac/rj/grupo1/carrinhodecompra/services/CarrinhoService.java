package br.senac.rj.grupo1.carrinhodecompra.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.rj.grupo1.carrinhodecompra.exception.EntidadeNaoEncontradaException;
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
		carrinhoRepository.FinalizarCarrinhoById(id);
	}
	
	public Carrinho getCarrinhoById(int id) {
		return carrinhoRepository.findByIdAndStatusNot(id, -1)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Carrinho com ID " + id + " não encontrado"));
	}
	
	public List<Carrinho> getAllCarrinhos(){
		return carrinhoRepository.findCarrinhosAtivos();
	}
}