package com.example.projectBancoDados.services;

import com.example.projectBancoDados.dto.cliente.ClienteDTO;
import com.example.projectBancoDados.entities.Cliente;
import com.example.projectBancoDados.exceptions.NotFoundException;
import com.example.projectBancoDados.repositories.ClienteRepository;
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
public class ClienteService {
    private final ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<ClienteDTO> findAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.ASC, "nome");
        List<Cliente> list = repository.findAll();
        return new PageImpl<>(list.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList()), pageRequest, size);
    }

    public ClienteDTO findById(Long id) {
        Cliente entity = repository.findById(id).orElseThrow(NotFoundException::new);
        return new ClienteDTO(entity);
    }

    @Transactional
    public ClienteDTO insert(ClienteDTO dto) {
        Cliente entity = new Cliente();
        entity.setTipo(dto.getTipo());
        entity.setNome(dto.getNome());
        entity.setDocumento(dto.getDocumento());
        entity.setEndereco(dto.getEndereco());
        entity.setTelefone(dto.getTelefone());
        entity = repository.save(entity);
        return new ClienteDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else throw new NotFoundException();
    }
}
