package com.example.projectBancoDados.dto.produto;

import com.example.projectBancoDados.entities.Produto;
import com.example.projectBancoDados.entities.ProdutoVenda;

import java.math.BigDecimal;

public class ProdutoResponse {

    private Long id;

    private String nome;

    private BigDecimal preco;

    private Integer quantidade;

    public ProdutoResponse() {}

    public ProdutoResponse(Produto entity) {
        id = entity.getId();
        nome = entity.getNome();
        preco = entity.getPreco();
        quantidade = entity.getQuantidade();
    }

    public ProdutoResponse(ProdutoVenda entity) {
        id = entity.getProduto().getId();
        nome = entity.getProduto().getNome();
        preco = entity.getProduto().getPreco();
        quantidade = entity.getQuantidade();
    }

    public Long getId() {
        return id;
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
