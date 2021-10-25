package com.example.projectBancoDados.services;

import com.example.projectBancoDados.dto.operadora.OperadoraDTO;
import com.example.projectBancoDados.entities.Operadora;
import com.example.projectBancoDados.exceptions.NotFoundException;
import com.example.projectBancoDados.repositories.OperadoraRepository;
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
public class OperadoraService {
    private final OperadoraRepository repository;

    @Autowired
    public OperadoraService(OperadoraRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<OperadoraDTO> findAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.ASC, "nome");
        List<Operadora> list = repository.findAll();
        return new PageImpl<>(list.stream().map(x -> new OperadoraDTO(x)).collect(Collectors.toList()), pageRequest, size);
    }

    public OperadoraDTO findById(Long id) {
        Operadora entity = repository.findById(id).orElseThrow(NotFoundException::new);
        return new OperadoraDTO(entity);
    }

    @Transactional
    public OperadoraDTO insert(OperadoraDTO dto) {
        Operadora entity = new Operadora();
        entity.setNome(dto.getNome());
        entity.setBandeira(dto.getBandeira());
        entity = repository.save(entity);
        return new OperadoraDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else throw new NotFoundException();
    }
}
