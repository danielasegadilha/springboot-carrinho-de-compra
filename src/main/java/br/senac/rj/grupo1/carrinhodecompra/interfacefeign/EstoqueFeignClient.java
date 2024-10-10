package br.senac.rj.grupo1.carrinhodecompra.interfacefeign;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.senac.rj.grupo1.carrinhodecompra.dto.EstoqueDTO;
import br.senac.rj.grupo1.carrinhodecompra.dto.ItemCarrinhoDTO;
import br.senac.rj.grupo1.carrinhodecompra.entities.ItemCarrinho;

@Component
@FeignClient (name="catalogoProdutos", url="10.136.36.87:8080", path="/estoque")
public interface EstoqueFeignClient {
	@GetMapping(value = "/{id}")
	public ResponseEntity<EstoqueDTO>findById(@PathVariable Long produtoId);
	
	@PutMapping("/{cartId}")
    public ResponseEntity<Void> updateEstoqueById(@PathVariable int cartId, @RequestBody int quantidade);
}
