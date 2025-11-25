package com.api.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.agenda.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    
}
