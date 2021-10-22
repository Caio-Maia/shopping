package com.example.projectBancoDados.controllers;

import com.example.projectBancoDados.dto.fornecedor.FornecedorDTO;
import com.example.projectBancoDados.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<FornecedorDTO>> findAll() {
        List<FornecedorDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FornecedorDTO> findById(@PathVariable Long id) {
        FornecedorDTO fornecedor = service.findById(id);
        return ResponseEntity.ok().body(fornecedor);
    }

    @PostMapping
    public ResponseEntity<FornecedorDTO> insert(@Valid @RequestBody FornecedorDTO dto) throws Exception {
        FornecedorDTO fornecedor = service.insert(dto);
        return ResponseEntity.ok().body(fornecedor);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
