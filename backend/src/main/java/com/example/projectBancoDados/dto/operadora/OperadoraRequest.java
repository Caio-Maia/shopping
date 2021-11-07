package com.example.projectBancoDados.dto.operadora;

import com.example.projectBancoDados.entities.Operadora;

import javax.validation.constraints.NotBlank;

public class OperadoraRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String bandeira;

    public OperadoraRequest() {
    }

    public OperadoraRequest(Operadora entity) {
        this.nome = entity.getNome();
        this.bandeira = entity.getBandeira();
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
