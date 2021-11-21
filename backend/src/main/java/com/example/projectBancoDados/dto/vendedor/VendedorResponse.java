package com.example.projectBancoDados.dto.vendedor;

import com.example.projectBancoDados.entities.Vendedor;
import com.example.projectBancoDados.entities.enums.Cargo;

public class VendedorResponse {

    private Long id;
    private String nome;
    private Long matricula;
    private Cargo cargo;

    public VendedorResponse() {}

    public VendedorResponse(Vendedor entity) {
        id = entity.getId();
        nome = entity.getNome();
        matricula = entity.getMatricula();
        //cargo = entity.getCargo();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getMatricula() {
        return matricula;
    }

    /*public Cargo getCargo() {
        return cargo;
    }*/
}
