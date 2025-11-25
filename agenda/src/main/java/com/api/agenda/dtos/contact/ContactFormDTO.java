package com.api.agenda.dtos.contact;

import java.util.List;

import com.api.agenda.dtos.telephone.TelephoneFormDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContactFormDTO(

    @NotBlank
    String firstName,

    @NotBlank
    String lastName,

    @NotBlank
    String email,

    @NotNull
    List<TelephoneFormDTO> numbers

) {}
