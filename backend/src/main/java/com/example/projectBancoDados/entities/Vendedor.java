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
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Long matricula;
/* Retirado de acordo com reunião 15/11
    @Column(nullable = false)
    private Cargo cargo;
*/
    public Vendedor() {
    }

    public Vendedor(Long id, String nome, Long matricula/*, Cargo cargo*/) {
        super();
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        //this.cargo = cargo;
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

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
/*
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }*/
}
