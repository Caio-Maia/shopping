package com.example.projectBancoDados.dto.pedido;

import com.example.projectBancoDados.entities.Pedido;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public class PedidoRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Positive
    @NotNull(message = "Quantidade is required.")
    private Integer quantidade;
    @NotNull(message = "ProdutoId is required.")
    private Long produtoId;
    @NotNull(message = "FornecedorId is required.")
    private Long fornecedorId;

    public PedidoRequest() {
    }

    public PedidoRequest(Pedido entity) {
        quantidade = entity.getQuantidade();
        produtoId = entity.getProduto().getId();
        fornecedorId = entity.getFornecedor().getId();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }
}
