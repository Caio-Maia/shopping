package com.example.projectBancoDados.services;

import com.example.projectBancoDados.dto.produto.ProdutoResponse;
import com.example.projectBancoDados.dto.produto.ProdutoRequest;
import com.example.projectBancoDados.entities.Produto;
import com.example.projectBancoDados.exceptions.NotFoundException;
import com.example.projectBancoDados.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<ProdutoResponse> findAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.ASC, "nome");
        List<Produto> list = repository.findAll();
        return new PageImpl<>(list.stream().map(x -> new ProdutoResponse(x)).collect(Collectors.toList()), pageRequest, size);
    }

    public ProdutoResponse findById(Long id) {
        Produto entity = repository.findById(id).orElseThrow(NotFoundException::new);
        return new ProdutoResponse(entity);
    }

    @Transactional
    public ProdutoResponse insert(ProdutoRequest dto) {
        Produto entity = new Produto();
        entity.setNome(dto.getNome());
        entity.setPreco(dto.getPreco());
        entity.setQuantidade(dto.getQuantidade());
        entity = repository.save(entity);
        return new ProdutoResponse(entity);
    }

    @Transactional
    public void delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else throw new NotFoundException();
    }
}
