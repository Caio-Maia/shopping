package com.example.projectBancoDados.dto.pedido;

import com.example.projectBancoDados.dto.produto.ProdutoDTO;
import com.example.projectBancoDados.dto.fornecedor.FornecedorDTO;
import com.example.projectBancoDados.entities.Pedido;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PedidoResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer quantidade;
    private LocalDateTime tempo;
    private ProdutoDTO produto;
    private FornecedorDTO fornecedor;

    public PedidoResponse() {
    }

    public PedidoResponse(Pedido entity) {
        id = entity.getId();
        quantidade = entity.getQuantidade();
        tempo = entity.getDataPedido();
        produto = new ProdutoDTO(entity.getProduto());
        fornecedor = new FornecedorDTO(entity.getFornecedor());
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

    public ProdutoDTO getProduto() {
        return produto;
    }

    public FornecedorDTO getFornecedor() {
        return fornecedor;
    }
}
