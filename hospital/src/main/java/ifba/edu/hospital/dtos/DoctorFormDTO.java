package ifba.edu.hospital.dtos;

import ifba.edu.hospital.entities.Address;
import ifba.edu.hospital.entities.Doctor;
import ifba.edu.hospital.enums.Specialty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorFormDTO(
    
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
    Address address ) 
    
{

    public DoctorFormDTO(Doctor doctor) {
        this(doctor.getCrm(), doctor.getName(), doctor.getEmail(), doctor.getCellphone(), doctor.getSpecialty(), doctor.getAddress());

    }
}
