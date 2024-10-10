package br.senac.rj.grupo1.carrinhodecompra.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.rj.grupo1.carrinhodecompra.entities.Carrinho;
import br.senac.rj.grupo1.carrinhodecompra.services.CarrinhoService;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {
	
	private final CarrinhoService carrinhoService;

	public CarrinhoController(CarrinhoService carrinhoService) {
		this.carrinhoService = carrinhoService;
	}
	
	@PostMapping("/{user_id}")
	public ResponseEntity<Carrinho> createCarrinho(@PathVariable int user_id){
		Carrinho carrinhoCriado = carrinhoService.createCarrinho(user_id);
		return new ResponseEntity<>(carrinhoCriado, HttpStatus.CREATED);
				
	}
	
	
	@PutMapping("/api/carrinho/{cart_id}/finalizar")
	public ResponseEntity<Void> finalizarCarrinhoById(@PathVariable int cart_id){
		carrinhoService.finalizaCarrinhoById(cart_id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{cart_id}")
	public ResponseEntity<Void> deleteCarrinhoById(@PathVariable int cart_id){
		carrinhoService.removeCarrinhoById(cart_id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{cart_id}")
	public ResponseEntity<Carrinho> getCarrinhoById(@PathVariable int cart_id){
		Carrinho carrinhoEncontrado = carrinhoService.getCarrinhoById(cart_id);
		return ResponseEntity.ok(carrinhoEncontrado);
	}
	
	@GetMapping
	public ResponseEntity<List<Carrinho>> getAllCarrinhos(){
		List<Carrinho> carrinhos = carrinhoService.getAllCarrinhos();
		return ResponseEntity.ok(carrinhos);
	}
}
