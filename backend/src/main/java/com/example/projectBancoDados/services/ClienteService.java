package com.example.projectBancoDados.services;

import com.example.projectBancoDados.dto.cliente.ClienteRequest;
import com.example.projectBancoDados.dto.cliente.ClienteResponse;
import com.example.projectBancoDados.entities.Cliente;
import com.example.projectBancoDados.entities.enums.Tipo;
import com.example.projectBancoDados.exceptions.NotFoundException;
import com.example.projectBancoDados.repositories.ClienteRepository;
import com.example.projectBancoDados.utils.ValidaDocumentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.example.projectBancoDados.utils.ValidaDocumentos.isCNPJ;
import static com.example.projectBancoDados.utils.ValidaDocumentos.isCPF;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<ClienteResponse> findAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.ASC, "nome");
        List<Cliente> list = repository.findAll();
        return new PageImpl<>(list.stream().map(x -> new ClienteResponse(x)).collect(Collectors.toList()), pageRequest, size);
    }

    @Transactional(readOnly = true)
    public ClienteResponse findById(Long id) {
        Cliente entity = repository.findById(id).orElseThrow(NotFoundException::new);
        return new ClienteResponse(entity);
    }

    @Transactional
    public ClienteResponse insert(ClienteRequest dto) {
        Pattern regex = Pattern.compile("(\\d{2})?9\\d{8}");
        if(dto.getTipo().equals(Tipo.PESSOA_FISICA)) {
            if(!isCPF(dto.getDocumento())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CPF do cliente não é válido.");
        }
        if(dto.getTipo().equals(Tipo.PESSOA_JURIDICA)) {
            if(!isCNPJ(dto.getDocumento())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CNPJ do cliente não é válido.");
        }
        if(repository.existsByDocumento(dto.getDocumento()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um cliente já está cadastrado com esse documento.");
        if(!regex.matcher(dto.getTelefone().toString()).matches())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O telefone do cliente não é valido.");
        Cliente entity = new Cliente();
        entity.setTipo(dto.getTipo());
        entity.setNome(dto.getNome());
        entity.setDocumento(dto.getDocumento());
        entity.setEndereco(dto.getEndereco());
        entity.setTelefone(dto.getTelefone());
        entity = repository.save(entity);
        return new ClienteResponse(entity);
    }

    @Transactional
    public void delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else throw new NotFoundException();
    }
}
