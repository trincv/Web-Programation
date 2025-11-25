package com.api.agenda.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginFormDTO(

    @NotBlank
    String username,

    @NotBlank
    String password
) {}
