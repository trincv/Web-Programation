package com.api.agenda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.agenda.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    public UserDetails findByUsername(String username);
    public List<User> findAll();

    
}
