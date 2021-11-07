package com.example.projectBancoDados.dto.vendedor;

import com.example.projectBancoDados.entities.Vendedor;
import com.example.projectBancoDados.entities.enums.Cargo;

public class VendedorResponse {

    private long id;
    private String nome;
    private long matricula;
    private Cargo cargo;

    public VendedorResponse() {}

    public VendedorResponse(Vendedor entity) {
        id = entity.getId();
        nome = entity.getNome();
        matricula = entity.getMatricula();
        cargo = entity.getCargo();
    }

    public long getId() {
        return id;
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
