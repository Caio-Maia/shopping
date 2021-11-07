package com.example.projectBancoDados.dto.cliente;

import com.example.projectBancoDados.entities.Cliente;
import com.example.projectBancoDados.entities.enums.Tipo;

public class ClienteResponse {

    private Long id;
    private Tipo tipo;
    private String nome;
    private Long documento;
    private String endereco;
    private Long telefone;

    public ClienteResponse() {
    }

    public ClienteResponse(Cliente entity) {
        this.id = entity.getId();
        this.tipo = entity.getTipo();
        this.nome = entity.getNome();
        this.documento = entity.getDocumento();
        this.endereco = entity.getEndereco();
        this.telefone = entity.getTelefone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }
}
