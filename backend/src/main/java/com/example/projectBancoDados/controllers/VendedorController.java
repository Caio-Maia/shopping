package com.example.projectBancoDados.controllers;

import com.example.projectBancoDados.dto.vendedor.VendedorDTO;
import com.example.projectBancoDados.services.VendedorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<VendedorDTO>> findAll(Pageable pageable) {
        Page<VendedorDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // buscar por matricula ao inves de id;
    @ApiOperation(value = "Retorna um vendedor especifico.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<VendedorDTO> findById(@PathVariable Long id) {
        VendedorDTO vendedor = service.findById(id);
        return ResponseEntity.ok().body(vendedor);
    }

    @ApiOperation(value = "Cria um novo vendedor.")
    @PostMapping
    public ResponseEntity<VendedorDTO> insert(@Valid @RequestBody VendedorDTO dto) {
        VendedorDTO vendedor = service.insert(dto);
        return ResponseEntity.ok().body(vendedor);
    }

    @ApiOperation(value = "Exclui um vendedor.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
