package com.example.projectBancoDados.dto.vendedor;

import com.example.projectBancoDados.entities.Vendedor;
import com.example.projectBancoDados.entities.enums.Cargo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VendedorRequest {

    @NotBlank(message = "Nome is required.")
    private String nome;
    @NotNull(message = "Matricula is required.")
    private long matricula;
    private Cargo cargo;

    public VendedorRequest() {}

    public VendedorRequest(Vendedor entity) {
        nome = entity.getNome();
        matricula = entity.getMatricula();
        cargo = entity.getCargo();
    }

    public String getNome() {
        return nome;
    }

    public long getMatricula() {
        return matricula;
    }

    public Cargo getCargo() {
        return cargo;
    }
}
