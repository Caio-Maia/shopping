package com.example.projectBancoDados.dto.produto;

import com.example.projectBancoDados.entities.Produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Nome is required.")
    private String nome;

    @NotNull(message = "Quantidade is required.")
    private Integer quantidade;

    public ProdutoDTO() {}

    public ProdutoDTO(Produto entity) {
        id = entity.getId();
        nome = entity.getNome();
        quantidade = entity.getQuantidade();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
