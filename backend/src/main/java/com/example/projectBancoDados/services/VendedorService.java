package com.example.projectBancoDados.services;

import com.example.projectBancoDados.dto.vendedor.VendedorDTO;
import com.example.projectBancoDados.entities.Vendedor;
import com.example.projectBancoDados.exceptions.NotFoundException;
import com.example.projectBancoDados.repositories.VendedorRepository;
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
public class VendedorService {

    private final VendedorRepository repository;

    @Autowired
    public VendedorService(VendedorRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<VendedorDTO> findAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.ASC, "nome");
        List<Vendedor> list = repository.findAll();
        return new PageImpl<>(list.stream().map(x -> new VendedorDTO(x)).collect(Collectors.toList()), pageRequest, size);
    }

    public VendedorDTO findById(Long id) {
        Vendedor entity = repository.findById(id).orElseThrow(NotFoundException::new);
        return new VendedorDTO(entity);
    }

    @Transactional
    public VendedorDTO insert(VendedorDTO dto) {
        Vendedor entity = new Vendedor();
        entity.setNome(dto.getNome());
        entity.setMatricula(dto.getMatricula());
        entity.setCargo(dto.getCargo());
        entity = repository.save(entity);
        return new VendedorDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else throw new NotFoundException();
    }
}
