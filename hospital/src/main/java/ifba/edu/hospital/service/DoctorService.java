package ifba.edu.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;
import ifba.edu.hospital.entities.Doctor;
import ifba.edu.hospital.repository.DoctorRepository;

@Service
public class DoctorService {

    private final DoctorRepository DoctorRepository;

    public DoctorService(DoctorRepository DoctorRepository) {
        this.DoctorRepository = DoctorRepository;
    }

    public Doctor saveDoctor(Doctor doctor) {
        return DoctorRepository.save(doctor);
    }

    public List<Doctor> findAllDoctor() {
        return DoctorRepository.findAll();
    }

}
