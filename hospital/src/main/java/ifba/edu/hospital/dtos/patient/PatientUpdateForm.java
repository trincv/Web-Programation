package ifba.edu.hospital.dtos.patient;

import ifba.edu.hospital.dtos.address.AddressFormDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateForm(

    @NotBlank(message = "The name can't be blank")
    String name,

    @NotBlank(message = "The cellphone can't be blank")
    String cellphone,

    @NotNull(message = "The address can't be null")
    AddressFormDTO address
) {} 
