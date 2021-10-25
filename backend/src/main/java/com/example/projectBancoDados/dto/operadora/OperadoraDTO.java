package com.example.projectBancoDados.dto.operadora;

import com.example.projectBancoDados.entities.Operadora;

import javax.validation.constraints.NotBlank;

public class OperadoraDTO {

    private long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String bandeira;

    public OperadoraDTO() {
    }

    public OperadoraDTO(Operadora entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.bandeira = entity.getBandeira();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
