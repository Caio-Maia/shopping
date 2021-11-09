package com.example.projectBancoDados.controllers;

import com.example.projectBancoDados.dto.vendedor.VendedorRequest;
import com.example.projectBancoDados.dto.vendedor.VendedorResponse;
import com.example.projectBancoDados.services.VendedorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/vendedores")
@Api(value = "Vendedores Controller")
@CrossOrigin(origins = "*")
public class VendedorController {

    @Autowired
    private VendedorService service;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de vendedores.")
    public ResponseEntity<Page<VendedorResponse>> findAll(Pageable pageable) {
        Page<VendedorResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // buscar por matricula ao inves de id;
    @ApiOperation(value = "Retorna um vendedor especifico.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<VendedorResponse> findById(@PathVariable Long id) {
        VendedorResponse vendedor = service.findById(id);
        return ResponseEntity.ok().body(vendedor);
    }

    @ApiOperation(value = "Cria um novo vendedor.")
    @PostMapping
    public ResponseEntity<VendedorResponse> insert(@Valid @RequestBody VendedorRequest dto) {
        VendedorResponse vendedor = service.insert(dto);
        return new ResponseEntity<>(vendedor, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Exclui um vendedor.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
