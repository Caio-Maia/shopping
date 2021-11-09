package com.example.projectBancoDados.controllers;

import com.example.projectBancoDados.dto.fornecedor.FornecedorRequest;
import com.example.projectBancoDados.dto.fornecedor.FornecedorResponse;
import com.example.projectBancoDados.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService service;

    @GetMapping
    public ResponseEntity<List<FornecedorResponse>> findAll() {
        List<FornecedorResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FornecedorResponse> findById(@PathVariable Long id) {
        FornecedorResponse fornecedor = service.findById(id);
        return ResponseEntity.ok().body(fornecedor);
    }

    @PostMapping
    public ResponseEntity<FornecedorResponse> insert(@Valid @RequestBody FornecedorRequest dto) throws Exception {
        FornecedorResponse fornecedor = service.insert(dto);
        return new ResponseEntity<>(fornecedor, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
