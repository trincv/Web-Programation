package ifba.edu.hospital.dtos.doctor;

import ifba.edu.hospital.dtos.address.AddressFormDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateForm(

    @NotBlank(message = "Name cannot be blank")
    String name,

    @NotBlank(message = "The cellphone canoot be blank")
    String cellphone,

    @NotNull(message = "The address cannot be null")
    AddressFormDTO address
) {}
