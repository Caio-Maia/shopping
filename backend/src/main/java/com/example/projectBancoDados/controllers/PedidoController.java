package com.example.projectBancoDados.controllers;

import com.example.projectBancoDados.dto.pedido.PedidoRequest;
import com.example.projectBancoDados.dto.pedido.PedidoResponse;
import com.example.projectBancoDados.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<Page<PedidoResponse>> findAll(Pageable pageable) {
        Page<PedidoResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> insert(@Valid @RequestBody PedidoRequest dto) {
        PedidoResponse pedido = service.insert(dto);
        return ResponseEntity.ok().body(pedido);
    }
}
