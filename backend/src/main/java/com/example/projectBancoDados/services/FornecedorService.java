package com.example.projectBancoDados.services;

import com.example.projectBancoDados.dto.fornecedor.FornecedorDTO;
import com.example.projectBancoDados.entities.Fornecedor;
import com.example.projectBancoDados.exceptions.NotFoundException;
import com.example.projectBancoDados.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;

    @Autowired
    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<FornecedorDTO> findAll() {
        List<Fornecedor> list = repository.findAll();
        return list.stream().map(x -> new FornecedorDTO(x)).collect(Collectors.toList());
    }

    public FornecedorDTO findById(Long id) {
        Fornecedor entity = repository.findById(id).orElseThrow(NotFoundException::new);
        return new FornecedorDTO(entity);
    }

    @Transactional
    public FornecedorDTO insert(FornecedorDTO dto) {
        Fornecedor entity = new Fornecedor();
        entity.setNome(dto.getNome());
        entity.setCnpj(dto.getCnpj());
        entity.setEndereco(dto.getEndereco());
        entity.setTelefone(dto.getTelefone());
        entity = repository.save(entity);
        return new FornecedorDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else throw new NotFoundException();
    }
}
