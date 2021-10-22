package com.example.projectBancoDados.services;

import com.example.projectBancoDados.dto.pedido.PedidoRequest;
import com.example.projectBancoDados.dto.pedido.PedidoResponse;
import com.example.projectBancoDados.entities.Fornecedor;
import com.example.projectBancoDados.entities.Pedido;
import com.example.projectBancoDados.entities.Produto;
import com.example.projectBancoDados.exceptions.NotFoundException;
import com.example.projectBancoDados.repositories.FornecedorRepository;
import com.example.projectBancoDados.repositories.PedidoRepository;
import com.example.projectBancoDados.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository,
                         ProdutoRepository produtoRepository,
                         FornecedorRepository fornecedorRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    @Transactional(readOnly = true)
    public Page<PedidoResponse> findAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.ASC, "nome");
        List<Pedido> list = pedidoRepository.findAll();
        return new PageImpl<>(list.stream().map(x -> new PedidoResponse(x)).collect(Collectors.toList()), pageRequest, size);
    }

    @Transactional
    public PedidoResponse insert(PedidoRequest dto) {
        if(fornecedorRepository.existsById(dto.getFornecedorId()) && produtoRepository.existsById(dto.getProdutoId())) {
            Pedido entity = new Pedido();
            Fornecedor fornecedor = fornecedorRepository.getById(dto.getFornecedorId());
            entity.setFornecedor(fornecedor);
            Produto produto = produtoRepository.getById(dto.getProdutoId());
            entity.setProduto(produto);
            entity.setQuantidade(dto.getQuantidade());
            entity.setTempo(LocalDateTime.now());
            entity = pedidoRepository.save(entity);
            produto.setQuantidade(produto.getQuantidade() + dto.getQuantidade());
            produtoRepository.save(produto);
            return new PedidoResponse(entity);
        }
        else throw new NullPointerException();
    }
}
