package com.example.projectBancoDados.services;

import com.example.projectBancoDados.dto.venda.VendaRequest;
import com.example.projectBancoDados.dto.venda.VendaResponse;
import com.example.projectBancoDados.entities.*;
import com.example.projectBancoDados.exceptions.NotFoundException;
import com.example.projectBancoDados.repositories.ClienteRepository;
import com.example.projectBancoDados.repositories.ProdutoRepository;
import com.example.projectBancoDados.repositories.VendaRepository;
import com.example.projectBancoDados.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;
    private final ProdutoRepository produtoRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository,
                        ClienteRepository clienteRepository,
                        VendedorRepository vendedorRepository,
                        ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.vendedorRepository = vendedorRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public Page<VendaResponse> findAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.ASC, "nome");
        List<Venda> list = vendaRepository.findAll();
        return new PageImpl<>(list.stream().map(x -> new VendaResponse(x)).collect(Collectors.toList()), pageRequest, size);
    }

    @Transactional
    public VendaResponse insert(VendaRequest dto) {
        if(clienteRepository.existsById(dto.getClienteId()) && vendedorRepository.existsById(dto.getVendedorId())) {
            Venda entity = new Venda();
            List<ProdutoVenda> entityProdutos = new ArrayList<>();
            dto.getProdutos().forEach(produto -> {
                if(!produtoRepository.existsById(produto.getId())) throw new NotFoundException();
                Produto newProduto = produtoRepository.getById(produto.getId());
                int novaQuantidade = newProduto.getQuantidade() - produto.getQuantidade();
                // Mudar o exception
                if(novaQuantidade < 0) throw new NotFoundException();
                entityProdutos.add(new ProdutoVenda(newProduto, produto.getQuantidade(), entity));
                newProduto.setQuantidade(novaQuantidade);
                produtoRepository.save(newProduto);
            });
            entity.setProdutos(entityProdutos);
            Cliente cliente = clienteRepository.getById(dto.getClienteId());
            entity.setCliente(cliente);
            Vendedor vendedor = vendedorRepository.getById(dto.getVendedorId());
            entity.setVendedor(vendedor);
            entity.setValor(10000);
            entity.setDataVenda(LocalDateTime.now());
            vendaRepository.save(entity);
            return new VendaResponse(entity);
        } else throw new NotFoundException();
    }
}
