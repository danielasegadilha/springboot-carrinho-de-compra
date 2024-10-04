package br.senac.rj.grupo1.carrinhodecompra.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.rj.grupo1.carrinhodecompra.entities.Carrinho;
import br.senac.rj.grupo1.carrinhodecompra.services.CarrinhoService;

@RestController
@RequestMapping("/api/usuarios")
public class CarrinhoController {
	
	private final CarrinhoService carrinhoService;

	public CarrinhoController(CarrinhoService carrinhoService) {
		this.carrinhoService = carrinhoService;
	}
	
	@PostMapping
	public ResponseEntity<Carrinho> createCarrinho(@RequestBody Carrinho carrinho){
		Carrinho carrinhoCriado = carrinhoService.createCarrinho(carrinho);
		return new ResponseEntity<>(carrinho, HttpStatus.CREATED);
				
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Carrinho> updateUsuario(@PathVariable int id, @RequestBody Carrinho carrinho){
		carrinho.setId(id);
		Carrinho carrinhoAtualizado = carrinhoService.updateCarrinho(carrinho);
		return ResponseEntity.ok(carrinhoAtualizado);
	}
	
	@PutMapping("/api/usuarios/{id}/finalizar")
	public ResponseEntity<Void> finalizarCarrinhoById(@PathVariable int id){
		carrinhoService.finalizaCarrinhoById(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCarrinhoById(@PathVariable int id){
		carrinhoService.removeCarrinhoById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Carrinho> getCarrinhoById(@PathVariable int id){
		Carrinho carrinhoEncontrado = carrinhoService.getCarrinhoById(id);
		return ResponseEntity.ok(carrinhoEncontrado);
	}
	
	@GetMapping
	public ResponseEntity<List<Carrinho>> getAllCarrinhos(){
		List<Carrinho> carrinhos = carrinhoService.getAllCarrinhos();
		return ResponseEntity.ok(carrinhos);
	}
}
