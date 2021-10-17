package com.example.projectBancoDados.services;

import com.example.projectBancoDados.dto.ProdutoDTO;
import com.example.projectBancoDados.entities.Produto;
import com.example.projectBancoDados.exceptions.NotFoundException;
import com.example.projectBancoDados.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ProdutoDTO> findAll() {
        List<Produto> list = repository.findAll();
        return list.stream().map(x -> new ProdutoDTO(x)).collect(Collectors.toList());
    }

    public ProdutoDTO findById(Long id) {
        Produto entity = repository.findById(id).orElseThrow(NotFoundException::new);
        return new ProdutoDTO(entity);
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto entity = new Produto();
        entity.setNome(dto.getNome());
        entity.setQuantidade(dto.getQuantidade());
        entity = repository.save(entity);
        return new ProdutoDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else throw new NotFoundException();
    }
}
