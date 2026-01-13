package ifba.edu.hospital.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ifba.edu.hospital.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {
    
    public Page<Patient> findAllByLoginActiveTrue(Pageable pagination);
}
