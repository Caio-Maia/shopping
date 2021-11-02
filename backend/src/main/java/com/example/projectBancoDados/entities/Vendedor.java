package com.example.projectBancoDados.entities;

import com.example.projectBancoDados.entities.enums.Cargo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_vendedor")
public class Vendedor implements Serializable {
    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private long matricula;
    private Cargo cargo;

    public Vendedor() {
    }

    public Vendedor(long id, String nome, long matricula, Cargo cargo) {
        super();
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.cargo = cargo;
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

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
