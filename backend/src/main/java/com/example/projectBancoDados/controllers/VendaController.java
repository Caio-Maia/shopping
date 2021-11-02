package com.example.projectBancoDados.controllers;

import com.example.projectBancoDados.dto.venda.VendaRequest;
import com.example.projectBancoDados.dto.venda.VendaResponse;
import com.example.projectBancoDados.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/vendas")
public class VendaController {
    @Autowired
    private VendaService service;

    @GetMapping
    public ResponseEntity<Page<VendaResponse>> findAll(Pageable pageable) {
        Page<VendaResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<VendaResponse> insert(@Valid @RequestBody VendaRequest dto) {
        VendaResponse venda = service.insert(dto);
        return ResponseEntity.ok().body(venda);
    }
}
