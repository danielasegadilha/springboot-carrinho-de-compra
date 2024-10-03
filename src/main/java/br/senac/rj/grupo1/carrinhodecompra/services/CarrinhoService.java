package br.senac.rj.grupo1.carrinhodecompra.services;

import br.senac.rj.grupo1.carrinhodecompra.entities.Carrinho;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CarrinhoService {

//    public BigDecimal calcularTotal(Carrinho carrinho) {
//        return carrinho.getItens().stream()
//                .map(item -> item.getProdutoId().getPreco().multiply(new BigDecimal(item.getQuantidade())))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }
}
