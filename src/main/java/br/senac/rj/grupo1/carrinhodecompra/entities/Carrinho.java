package br.senac.rj.grupo1.carrinhodecompra.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cart")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(columnDefinition = "VARCHAR", nullable = false)
    private int status;

    @Column(name = "total", columnDefinition = "DECIMAL")
    private BigDecimal valorTotal;

    private List<ItemCarrinho> itens;

//    public double calcularTotal() {
//        return itens.stream()
//                .mapToDouble(item -> item.getQuantidade() * item.getProduto().getPreco())
//                .sum();
//    }
}
