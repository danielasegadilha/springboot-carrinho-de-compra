package br.senac.rj.grupo1.carrinhodecompra.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(nullable = false)
    private int quantidade;
    @Column(name = "product_id", nullable = false)
    private long produtoId;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Carrinho carrinhoId;


//    public double getQuantidade() {
//    }
}
