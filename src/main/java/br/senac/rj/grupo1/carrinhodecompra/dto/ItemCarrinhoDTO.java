package br.senac.rj.grupo1.carrinhodecompra.dto;

public class ItemCarrinhoDTO {
    private Integer produtoId;
    private Integer quantidade;

    // Getters e Setters
    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

