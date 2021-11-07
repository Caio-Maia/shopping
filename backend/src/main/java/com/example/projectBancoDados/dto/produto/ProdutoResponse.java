package com.example.projectBancoDados.dto.produto;

import com.example.projectBancoDados.entities.Produto;
import com.example.projectBancoDados.entities.ProdutoVenda;

public class ProdutoResponse {

    private Long id;

    private String nome;

    private Integer quantidade;

    public ProdutoResponse() {}

    public ProdutoResponse(Produto entity) {
        id = entity.getId();
        nome = entity.getNome();
        quantidade = entity.getQuantidade();
    }

    public ProdutoResponse(ProdutoVenda entity) {
        id = entity.getProduto().getId();
        nome = entity.getProduto().getNome();
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
