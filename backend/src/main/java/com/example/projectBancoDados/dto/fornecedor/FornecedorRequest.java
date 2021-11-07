package com.example.projectBancoDados.dto.fornecedor;

import com.example.projectBancoDados.entities.Fornecedor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class FornecedorRequest {

    @NotBlank(message = "Nome is required.")
    private String nome;
    @NotEmpty(message = "CNPJ is required")
    private Long cnpj;
    @NotBlank(message = "Endereço is required")
    private String endereco;
    @NotEmpty(message = "Telefone is required")
    private Long telefone;

    public FornecedorRequest() {}

    public FornecedorRequest(Fornecedor entity) {
        nome = entity.getNome();
        cnpj = entity.getCnpj();
        endereco = entity.getEndereco();
        telefone = entity.getTelefone();
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
