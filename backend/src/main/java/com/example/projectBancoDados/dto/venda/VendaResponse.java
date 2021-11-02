package com.example.projectBancoDados.dto.venda;

import com.example.projectBancoDados.dto.cliente.ClienteDTO;
import com.example.projectBancoDados.dto.produto.ProdutoDTO;
import com.example.projectBancoDados.dto.vendedor.VendedorDTO;
import com.example.projectBancoDados.entities.Venda;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VendaResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private ClienteDTO cliente;
    private VendedorDTO vendedor;
    private List<ProdutoDTO> produtos;
    private Integer valor;
    private LocalDateTime dataVenda;

    public VendaResponse() {
    }

    public VendaResponse(Venda entity) {
        id = entity.getId();
        cliente = new ClienteDTO(entity.getCliente());
        vendedor = new VendedorDTO(entity.getVendedor());
        produtos = new ArrayList<>();
        entity.getProdutos().forEach(produto -> produtos.add(new ProdutoDTO(produto)));
        valor = entity.getValor();
        dataVenda = entity.getDataVenda();
    }

    public long getId() {
        return id;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public VendedorDTO getVendedor() {
        return vendedor;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public Integer getValor() {
        return valor;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }
}
