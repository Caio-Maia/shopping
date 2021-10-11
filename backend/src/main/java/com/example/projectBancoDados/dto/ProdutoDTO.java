package com.example.projectBancoDados.dto;

import com.example.projectBancoDados.entities.Produto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "nome is required.")
    private String nome;
    @NotNull(message = "quantidade is required.")
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
