package br.senac.rj.grupo1.carrinhodecompra.entities;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Carrinho {

    private List<ItemCarrinho> itens;

//    public double calcularTotal() {
//        return itens.stream()
//                .mapToDouble(item -> item.getQuantidade() * item.getProduto().getPreco())
//                .sum();
//    }
}
