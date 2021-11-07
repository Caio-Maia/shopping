package com.example.projectBancoDados.controllers;

import com.example.projectBancoDados.dto.operadora.OperadoraRequest;
import com.example.projectBancoDados.dto.operadora.OperadoraResponse;
import com.example.projectBancoDados.services.OperadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/operadoras")
public class OperadoraController {

    @Autowired
    private OperadoraService service;

    @GetMapping
    public ResponseEntity<Page<OperadoraResponse>> findAll(Pageable pageable) {
        Page<OperadoraResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OperadoraResponse> findById(@PathVariable Long id) {
        OperadoraResponse operadora = service.findById(id);
        return ResponseEntity.ok().body(operadora);
    }

    @PostMapping
    public ResponseEntity<OperadoraResponse> insert(@Valid @RequestBody OperadoraRequest dto) {
        OperadoraResponse operadora = service.insert(dto);
        return ResponseEntity.ok().body(operadora);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
