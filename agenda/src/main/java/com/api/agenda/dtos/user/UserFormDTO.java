package com.api.agenda.dtos.user;

import java.util.List;

import com.api.agenda.dtos.contact.ContactFormDTO;
import com.api.agenda.entities.Roles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserFormDTO(
    
    @NotBlank
    String username,

    @NotBlank
    String password,

    @NotNull
    Roles authorities,

    @NotNull
    List<ContactFormDTO> contacts

) {}
