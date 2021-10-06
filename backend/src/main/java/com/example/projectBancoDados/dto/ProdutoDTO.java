package com.example.projectBancoDados.dto;

import com.example.projectBancoDados.entities.Produto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Integer quantidade;
    private String image;

    public ProdutoDTO() {}

    public ProdutoDTO(Produto entity) {
        id = entity.getId();
        nome = entity.getNome();
        quantidade = entity.getQuantidade();
        image = entity.getImage();
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
