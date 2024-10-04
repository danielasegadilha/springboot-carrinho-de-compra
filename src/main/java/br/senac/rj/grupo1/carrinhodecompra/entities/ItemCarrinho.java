package br.senac.rj.grupo1.carrinhodecompra.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Carrinho carrinhoId;

    @Column(name = "product_id", nullable = false)
    private Long produtoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(long produtoId) {
        this.produtoId = produtoId;
    }

    public Carrinho getCarrinhoId() {
        return carrinhoId;
    }

    public void setCarrinhoId(Carrinho carrinhoId) {
        this.carrinhoId = carrinhoId;
    }

}
