package ifba.edu.hospital.dtos.patient;

import ifba.edu.hospital.dtos.address.AddressFormDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientFormDTO (

    @NotBlank(message = "Login is required")
    String userName,

    @NotBlank(message = "Password is required")
    String password,
    
    @NotBlank(message = "The cpf cannot be blank")
    String cpf,

    @NotBlank(message = "The name cannot be blank")
    String name,

    @NotBlank(message = "The email cannot be blank")
    String email,

    @NotBlank(message = "The cellphone cannot be blank")
    String cellphone,
    
    @NotNull(message = "The adress cannot be null")
    AddressFormDTO address 
) {}
