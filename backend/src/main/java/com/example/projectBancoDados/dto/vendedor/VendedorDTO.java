package com.example.projectBancoDados.dto.vendedor;

import com.example.projectBancoDados.entities.Vendedor;
import com.example.projectBancoDados.entities.enums.Cargo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class VendedorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    @NotBlank(message = "Nome is required.")
    private String nome;
    @NotNull(message = "Matricula is required.")
    private long matricula;
    private Cargo cargo;

    public VendedorDTO() {}

    public VendedorDTO(Vendedor entity) {
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
