package com.example.projectBancoDados.dto.pedido;

import com.example.projectBancoDados.dto.produto.ProdutoResponse;
import com.example.projectBancoDados.dto.fornecedor.FornecedorResponse;
import com.example.projectBancoDados.entities.Pedido;

import java.time.LocalDateTime;

public class PedidoResponse {

    private Long id;
    private Integer quantidade;
    private LocalDateTime tempo;
    private ProdutoResponse produto;
    private FornecedorResponse fornecedor;

    public PedidoResponse() {
    }

    public PedidoResponse(Pedido entity) {
        id = entity.getId();
        quantidade = entity.getQuantidade();
        tempo = entity.getDataPedido();
        produto = new ProdutoResponse(entity.getProduto());
        fornecedor = new FornecedorResponse(entity.getFornecedor());
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public LocalDateTime getTempo() {
        return tempo;
    }

    public ProdutoResponse getProduto() {
        return produto;
    }

    public FornecedorResponse getFornecedor() {
        return fornecedor;
    }
}
