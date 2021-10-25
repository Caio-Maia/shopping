package com.example.projectBancoDados.repositories;

import com.example.projectBancoDados.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
