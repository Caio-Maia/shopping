package com.example.projectBancoDados.dto.cliente;

import com.example.projectBancoDados.entities.Cliente;
import com.example.projectBancoDados.entities.enums.Tipo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ClienteRequest {

    private Tipo tipo;
    @NotBlank(message = "Nome is required.")
    @Pattern(regexp = "^([a-z][a-z\\s]*[a-z])$", message = "O nome não pode conter números ou catacteres especiais.")
    private String nome;
    @NotNull(message = "Documento is required.")
    private Long documento;
    @NotBlank(message = "Endereco is required.")
    private String endereco;
    @NotNull(message = "Telefone is required.")
    private Long telefone;

    public ClienteRequest() {
    }

    public ClienteRequest(Cliente entity) {
        this.tipo = entity.getTipo();
        this.nome = entity.getNome();
        this.documento = entity.getDocumento();
        this.endereco = entity.getEndereco();
        this.telefone = entity.getTelefone();
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
