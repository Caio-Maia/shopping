package com.example.projectBancoDados.dto.produto;

import com.example.projectBancoDados.entities.Produto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ProdutoExistBody implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "ProdutoId is required")
    private Long id;

    @NotNull(message = "Quantidade is required.")
    private Integer quantidade;

    public ProdutoExistBody() {}

    public ProdutoExistBody(Produto entity) {
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
