package com.example.projectBancoDados.dto.operadora;

import com.example.projectBancoDados.entities.Operadora;

import javax.validation.constraints.NotBlank;

public class OperadoraRequest {

    @NotBlank(message = "Nome is required.")
    private String nome;

    public OperadoraRequest() {
    }

    public OperadoraRequest(Operadora entity) {
        this.nome = entity.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
