package ifba.edu.hospital.dtos.login;

import jakarta.validation.constraints.NotBlank;

public record LoginFormDTO(@NotBlank String userName, @NotBlank String password) {}
