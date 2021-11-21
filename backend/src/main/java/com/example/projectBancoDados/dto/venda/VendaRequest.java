package com.example.projectBancoDados.dto.venda;

import com.example.projectBancoDados.dto.produto.ProdutoVendaBody;
import com.example.projectBancoDados.entities.Venda;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class VendaRequest {

    @NotEmpty(message = "Produtos is required")
    private List<ProdutoVendaBody> produtos;
    private Long vendedorId;
    @NotNull(message = "ClienteId is required.")
    private Long clienteId;
    private Long operadoraId;
    @NotNull(message = "Parcelas is required.")
    private Integer parcelas;

    public VendaRequest() {
    }

    public VendaRequest(Venda entity) {
        entity.getProdutos().forEach(produtoEntity -> {
            this.produtos.forEach(produto -> {
                produto.setId(produtoEntity.getId());
                produto.setQuantidade(produtoEntity.getQuantidade());
            });
        });
        vendedorId = entity.getVendedor().getId();
        clienteId = entity.getCliente().getId();
        operadoraId = entity.getOperadora().getId();
        parcelas = entity.getParcelas();
    }

    public List<ProdutoVendaBody> getProdutos() {
        return produtos;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getOperadoraId() {
        return operadoraId;
    }

    public Integer getParcelas() {
        return parcelas;
    }
}