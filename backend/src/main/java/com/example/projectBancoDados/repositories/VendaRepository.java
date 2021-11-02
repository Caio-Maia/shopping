package com.example.projectBancoDados.repositories;

import com.example.projectBancoDados.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
