package ifba.edu.hospital.dtos.doctor;

import ifba.edu.hospital.entities.Address;
import ifba.edu.hospital.entities.Doctor;
import ifba.edu.hospital.enums.Specialty;

public record DoctorDTO(String crm, String name, String email, String cellphone, Specialty specialty, Address address) {
    
    public DoctorDTO(Doctor doctor) {
        this(doctor.getCrm(), doctor.getName(), doctor.getEmail(), doctor.getCellphone(), doctor.getSpecialty(), doctor.getAddress());
    }
}
