package com.api.agenda.dtos.telephone;

import com.api.agenda.entities.TelephoneCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TelephoneFormDTO(

    @NotBlank
    String number,

    @NotNull
    boolean principal,

    @NotNull
    TelephoneCategory category

) {}
