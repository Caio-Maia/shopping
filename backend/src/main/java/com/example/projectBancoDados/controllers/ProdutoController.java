package com.example.projectBancoDados.controllers;

import com.example.projectBancoDados.dto.produto.ProdutoResponse;
import com.example.projectBancoDados.dto.produto.ProdutoRequest;
import com.example.projectBancoDados.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/produtos")
@Api(value = "Produtos Controller")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de produtos.")
    public ResponseEntity<Page<ProdutoResponse>> findAll(Pageable pageable) {
        Page<ProdutoResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "Retorna um produto especifico.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponse> findById(@PathVariable Long id) {
        ProdutoResponse produto = service.findById(id);
        return ResponseEntity.ok().body(produto);
    }

    @ApiOperation(value = "Cria um novo produto.")
    @PostMapping
    public ResponseEntity<ProdutoResponse> insert(@Valid @RequestBody ProdutoRequest dto) {
        ProdutoResponse produto = service.insert(dto);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza informações de um produto.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponse> update(@PathVariable Long id, @Valid @RequestBody ProdutoRequest dto) {
        ProdutoResponse produto = service.update(id, dto);
        return ResponseEntity.ok().body(produto);
    }

    // Não pode excluir o produto sem excluir os pedidos relacionados a ele.
    @ApiOperation(value = "Exclui um produto.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}