package com.api.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.agenda.entities.Telephone;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long>{
    
}
