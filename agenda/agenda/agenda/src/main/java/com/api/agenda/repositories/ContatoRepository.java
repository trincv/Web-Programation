package com.api.agenda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.agenda.entities.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    // JPA automaticamente gera a query: 
    // "SELECT c FROM Contato c WHERE c.usuario.username = :username"
    List<Contato> findByUsuarioUsername(String username);
}
