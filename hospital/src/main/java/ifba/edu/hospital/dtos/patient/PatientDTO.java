package ifba.edu.hospital.dtos.patient;

import ifba.edu.hospital.entities.Patient;

public record PatientDTO(String name, String email, String cpf) {

    public PatientDTO(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
