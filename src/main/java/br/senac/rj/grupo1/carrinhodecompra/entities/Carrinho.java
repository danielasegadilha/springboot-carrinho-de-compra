package br.senac.rj.grupo1.carrinhodecompra.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cart")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "dt_criacao")
    private LocalDateTime dataCriacao;
    @Column(columnDefinition = "VARCHAR", nullable = false)
    private String status;
    @Column(name = "total", columnDefinition = "DECIMAL")
    private BigDecimal valorTotal;
    @Column(name = "usuario_id", nullable = false)
    private long usuarioId;
    @OneToMany(mappedBy="carrinhoId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrinho> itens;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    //    public double calcularTotal() {
//        return itens.stream()
//                .mapToDouble(item -> item.getQuantidade() * item.getProduto().getPreco())
//                .sum();
//    }

}
