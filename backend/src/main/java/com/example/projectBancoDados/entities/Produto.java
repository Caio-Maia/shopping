package com.example.projectBancoDados.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_produto")
public class Produto implements Serializable {
    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "quantidade")
    private Integer quantidade;

    public Produto(){}

    public Produto(long id, String nome, Integer quantidade) {
        super();
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public long getId() {
        return id;
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
