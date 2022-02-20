package com.example.projectBancoDados.dto.fornecedor;

import com.example.projectBancoDados.entities.Fornecedor;

public class FornecedorResponse {

    private Long id;
    private String nome;
    private String cnpj;
    private String endereco;
    private Long telefone;

    public FornecedorResponse() {}

    public FornecedorResponse(Fornecedor entity) {
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

    public String getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public Long getTelefone() {
        return telefone;
    }
}
