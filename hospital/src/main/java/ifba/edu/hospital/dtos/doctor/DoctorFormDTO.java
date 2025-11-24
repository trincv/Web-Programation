package ifba.edu.hospital.dtos.doctor;

import ifba.edu.hospital.dtos.address.AddressFormDTO;
import ifba.edu.hospital.enums.Specialty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorFormDTO (

    @NotBlank(message = "Login is required")
    String userName,

    @NotBlank(message = "Password is required")
    String password,
    
    @NotBlank(message = "The crm cannot be null")
    String crm,

    @NotBlank(message = "The name cannot be null")
    String name,

    @NotBlank(message = "The email cannot be null")
    String email,

    @NotBlank(message = "The cellphone cannot be null")
    String cellphone,

    @NotNull(message = "The adress cannot be null")
    Specialty specialty,
    
    @NotNull(message = "The adress cannot be null")
    AddressFormDTO address )
{}
