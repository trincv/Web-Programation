package ifba.edu.hospital.dtos.address;

import jakarta.validation.constraints.NotBlank;

public record AddressFormDTO (

    @NotBlank(message = "The place cannot be blank")
    String place,

    @NotBlank(message = "The neighborhood cannot be blank")
    String neighborhood,

    @NotBlank(message = "The city cannot be blank")    
    String city,

    @NotBlank(message = "The estate cannot be blank") 
    String estate,

    @NotBlank(message = "The cep cannot be blank") 
    String cep,

    Integer number, 
    String complement ) 
{}
