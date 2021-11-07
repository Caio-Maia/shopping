package com.example.projectBancoDados.dto.produto;

import com.example.projectBancoDados.entities.Produto;
import com.example.projectBancoDados.entities.ProdutoVenda;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProdutoRequest {

    @NotBlank(message = "Nome is required.")
    private String nome;

    @Positive(message = "Quantidade must be greater than 0")
    @NotNull(message = "Quantidade is required.")
    private Integer quantidade;

    public ProdutoRequest() {}

    public ProdutoRequest(Produto entity) {
        nome = entity.getNome();
        quantidade = entity.getQuantidade();
    }

    public ProdutoRequest(ProdutoVenda entity) {
        nome = entity.getProduto().getNome();
        quantidade = entity.getQuantidade();
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
