package com.example.projectBancoDados.dto.venda;

import com.example.projectBancoDados.dto.cliente.ClienteResponse;
import com.example.projectBancoDados.dto.operadora.OperadoraResponse;
import com.example.projectBancoDados.dto.produto.ProdutoResponse;
import com.example.projectBancoDados.dto.vendedor.VendedorResponse;
import com.example.projectBancoDados.entities.Venda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VendaResponse {

    private Long id;
    private ClienteResponse cliente;
    private VendedorResponse vendedor;
    private List<ProdutoResponse> produtos;
    private OperadoraResponse operadora;
    private Integer parcelas;
    private BigDecimal valor;
    private LocalDateTime dataVenda;

    public VendaResponse() {
    }

    public VendaResponse(Venda entity) {
        id = entity.getId();
        cliente = new ClienteResponse(entity.getCliente());
        vendedor = new VendedorResponse(entity.getVendedor());
        produtos = new ArrayList<>();
        entity.getProdutos().forEach(produto -> produtos.add(new ProdutoResponse(produto)));
        operadora = new OperadoraResponse(entity.getOperadora());
        parcelas = entity.getParcelas();
        valor = entity.getValor();
        dataVenda = entity.getDataVenda();
    }

    public Long getId() {
        return id;
    }

    public ClienteResponse getCliente() {
        return cliente;
    }

    public VendedorResponse getVendedor() {
        return vendedor;
    }

    public List<ProdutoResponse> getProdutos() {
        return produtos;
    }

    public OperadoraResponse getOperadora() {
        return operadora;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }
}
