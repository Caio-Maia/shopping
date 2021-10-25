package com.example.projectBancoDados.controllers;

import com.example.projectBancoDados.dto.operadora.OperadoraDTO;
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
    public ResponseEntity<Page<OperadoraDTO>> findAll(Pageable pageable) {
        Page<OperadoraDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OperadoraDTO> findById(@PathVariable Long id) {
        OperadoraDTO operadora = service.findById(id);
        return ResponseEntity.ok().body(operadora);
    }

    @PostMapping
    public ResponseEntity<OperadoraDTO> insert(@Valid @RequestBody OperadoraDTO dto) {
        OperadoraDTO operadora = service.insert(dto);
        return ResponseEntity.ok().body(operadora);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
