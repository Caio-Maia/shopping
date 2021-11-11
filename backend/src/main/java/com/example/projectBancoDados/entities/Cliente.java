package com.example.projectBancoDados.entities;

import com.example.projectBancoDados.entities.enums.Tipo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_cliente")
public class Cliente implements Serializable {
    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Tipo tipo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Long documento;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private Long telefone;

    public Cliente() {
    }

    public Cliente(Long id, Tipo tipo, String nome, Long documento, String endereco, Long telefone) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }
}
