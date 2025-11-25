package com.api.agenda.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.agenda.dtos.user.UserFormDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; 
    
    private String username;
    private String password;
    private Roles authorities;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private List<Contact> contacts;

    public User (String username, String password, Roles authorities, List<Contact> contacts) {
        this.authorities = authorities;
        this.contacts = contacts;
        this.password = password;
        this.username = username;
    }

    public User (UserFormDTO userForm) {
        this.authorities = userForm.authorities();
        this.contacts = userForm.contacts().stream().map(Contact::new).toList();
        this.username = userForm.username();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        if (authorities == Roles.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRoleEnum() {
        return authorities;
    }

    public void setAuthorities(Roles authorities) {
        this.authorities = authorities;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

}