package com.example.projectBancoDados.dto.produto;

import com.example.projectBancoDados.entities.Produto;
import com.example.projectBancoDados.entities.ProdutoVenda;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProdutoRequest {

    @NotBlank(message = "Nome is required.")
    private String nome;

    @Positive(message = "Preco must be greater than 0")
    @NotNull(message = "Preco is required.")
    private BigDecimal preco;

    @Positive(message = "Quantidade must be greater than 0")
    @NotNull(message = "Quantidade is required.")
    private Integer quantidade;

    public ProdutoRequest() {}

    public ProdutoRequest(Produto entity) {
        nome = entity.getNome();
        preco = entity.getPreco();
        quantidade = entity.getQuantidade();
    }

    public ProdutoRequest(ProdutoVenda entity) {
        nome = entity.getProduto().getNome();
        preco = entity.getProduto().getPreco();
        quantidade = entity.getQuantidade();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
