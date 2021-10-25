package com.example.projectBancoDados.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_operadora")
public class Operadora implements Serializable {
    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    // Talvez seja melhor usar ENUMS?
    private String bandeira;

    public Operadora() {
    }

    public Operadora(long id, String nome, String bandeira) {
        this.id = id;
        this.nome = nome;
        this.bandeira = bandeira;
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
