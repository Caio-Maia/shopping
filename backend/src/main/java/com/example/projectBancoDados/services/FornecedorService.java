package com.example.projectBancoDados.services;

import com.example.projectBancoDados.dto.fornecedor.FornecedorRequest;
import com.example.projectBancoDados.dto.fornecedor.FornecedorResponse;
import com.example.projectBancoDados.entities.Fornecedor;
import com.example.projectBancoDados.exceptions.NotFoundException;
import com.example.projectBancoDados.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FornecedorService {

    private final FornecedorRepository repository;

    @Autowired
    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<FornecedorResponse> findAll() {
        List<Fornecedor> list = repository.findAll();
        return list.stream().map(x -> new FornecedorResponse(x)).collect(Collectors.toList());
    }

    public FornecedorResponse findById(Long id) {
        Fornecedor entity = repository.findById(id).orElseThrow(NotFoundException::new);
        return new FornecedorResponse(entity);
    }

    @Transactional
    public FornecedorResponse insert(FornecedorRequest dto) {
        if(repository.existsByCnpj(dto.getCnpj()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um fornecedor já está cadastrado com esse documento.");
        Pattern regex = Pattern.compile("(\\d{2})?9\\d{8}");
        if(isCNPJ(dto.getCnpj())) {
            if(regex.matcher(dto.getTelefone().toString()).matches()) {
                Fornecedor entity = new Fornecedor();
                entity.setNome(dto.getNome());
                entity.setCnpj(dto.getCnpj());
                entity.setEndereco(dto.getEndereco());
                entity.setTelefone(dto.getTelefone());
                entity = repository.save(entity);
                return new FornecedorResponse(entity);
            } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O telefone do fornecedor não é valido.");
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CNPJ do fornecedor não é válido.");
    }

    @Transactional
    public void delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else throw new NotFoundException();
    }
}
