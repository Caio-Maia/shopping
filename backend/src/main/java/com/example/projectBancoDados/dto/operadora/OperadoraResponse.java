package com.example.projectBancoDados.dto.operadora;

import com.example.projectBancoDados.entities.Operadora;

import javax.validation.constraints.NotBlank;

public class OperadoraResponse {

    private Long id;
    private String nome;
    private String bandeira;

    public OperadoraResponse() {
    }

    public OperadoraResponse(Operadora entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.bandeira = entity.getBandeira();
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

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }
}
