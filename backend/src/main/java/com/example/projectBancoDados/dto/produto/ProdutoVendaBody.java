package com.example.projectBancoDados.dto.produto;

import com.example.projectBancoDados.entities.Produto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProdutoVendaBody {

    @NotNull(message = "ProdutoId is required")
    private Long id;

    @NotNull(message = "Quantidade is required.")
    @Positive(message = "Quantidade must be greater than 0")
    private Integer quantidade;

    public ProdutoVendaBody() {}

    public ProdutoVendaBody(Produto entity) {
        id = entity.getId();
        quantidade = entity.getQuantidade();
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
