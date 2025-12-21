package ifba.edu.hospital.dtos.doctor;

import ifba.edu.hospital.entities.Doctor;
import ifba.edu.hospital.enums.Specialty;

public record DoctorDTO(String name, String email, String crm, Specialty specialty) {
    
    public DoctorDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
