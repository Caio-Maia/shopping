package com.example.projectBancoDados.dto.fornecedor;

import com.example.projectBancoDados.entities.Fornecedor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class FornecedorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Nome is required.")
    private String nome;
    @NotEmpty(message = "CNPJ is required")
    private Long cnpj;
    @NotEmpty(message = "Endereço is required")
    private String endereco;
    @NotEmpty(message = "Telefone is required")
    private Long telefone;

    public FornecedorDTO() {}

    public FornecedorDTO(Fornecedor entity) {
        id = entity.getId();
        nome = entity.getNome();
        cnpj = entity.getCnpj();
        endereco = entity.getEndereco();
        telefone = entity.getTelefone();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public Long getTelefone() {
        return telefone;
    }
}
