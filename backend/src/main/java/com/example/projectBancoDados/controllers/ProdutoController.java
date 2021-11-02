package com.example.projectBancoDados.controllers;

import com.example.projectBancoDados.dto.produto.ProdutoDTO;
import com.example.projectBancoDados.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/produtos")
@Api(value = "Produtos Controller")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de produtos.")
    public ResponseEntity<Page<ProdutoDTO>> findAll(Pageable pageable) {
        Page<ProdutoDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "Retorna um produto especifico.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
        ProdutoDTO produto = service.findById(id);
        return ResponseEntity.ok().body(produto);
    }

    @ApiOperation(value = "Cria um novo produto.")
    @PostMapping
    public ResponseEntity<ProdutoDTO> insert(@Valid @RequestBody ProdutoDTO dto) {
        ProdutoDTO produto = service.insert(dto);
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