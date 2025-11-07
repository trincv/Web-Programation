package ifba.edu.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifba.edu.hospital.entities.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,String> {
    
}
