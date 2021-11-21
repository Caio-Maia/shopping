package com.example.projectBancoDados.repositories;

import com.example.projectBancoDados.dto.produto.ProdutoResponse;
import com.example.projectBancoDados.entities.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
