package ifba.edu.hospital.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifba.edu.hospital.entities.Doctor;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor,String> {
    
    public Doctor findByLoginUserName(String userName);
    public Page<Doctor> findAllByLoginActiveTrue(Pageable pagination);
    public List<Doctor> findByName(String name);
}
