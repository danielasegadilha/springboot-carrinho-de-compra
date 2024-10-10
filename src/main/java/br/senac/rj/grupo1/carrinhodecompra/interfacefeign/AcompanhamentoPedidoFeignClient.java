package br.senac.rj.grupo1.carrinhodecompra.interfacefeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Component
@FeignClient (name="Av1", url="10.136.36.141:8080", path="/acompanhamento")
public interface AcompanhamentoPedidoFeignClient {
    @PostMapping("/{cartId}/atualizar?status=PREPARACAO")
    public ResponseEntity<Void> createAcompanhamentoPedido(@PathVariable int cartId);
}
