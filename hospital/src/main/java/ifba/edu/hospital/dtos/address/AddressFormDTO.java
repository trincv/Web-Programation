package ifba.edu.hospital.dtos.address;

import jakarta.validation.constraints.NotBlank;

public record AddressFormDTO (

    @NotBlank(message = "The place cannot be null")
    String place,

    @NotBlank(message = "The neighborhood cannot be null")
    String neighborhood,

    @NotBlank(message = "The city cannot be null")    
    String city,

    @NotBlank(message = "The estate cannot be null") 
    String estate,

    @NotBlank(message = "The cep cannot be null") 
    String cep,

    Integer number, 
    String complement ) 
{}
