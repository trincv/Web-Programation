package ifba.edu.hospital.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ifba.edu.hospital.dtos.DoctorDTO;
import ifba.edu.hospital.dtos.DoctorFormDTO;
import ifba.edu.hospital.entities.Doctor;
import ifba.edu.hospital.repository.DoctorRepository;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorDTO saveDoctor(DoctorFormDTO doctor) {

        var newDoctor = new Doctor(doctor);
        var savedDoctor = doctorRepository.save(newDoctor);

        return new DoctorDTO(savedDoctor);
    }

    public Page<DoctorDTO> findAllDoctor(Pageable pageable) {
        return doctorRepository.findAll(pageable).map(DoctorDTO::new);
    }

    public List<DoctorDTO> findDoctorByName(String name) {

        List<DoctorDTO> doctorList = doctorRepository.findByName(name).stream().map(DoctorDTO::new).toList();

        if (doctorList.isEmpty()) {
            return null;
        }

        return doctorList;
    }

    public DoctorDTO updateDoctor(String crm, DoctorFormDTO doctorForm) {

        var doctorFound = doctorRepository.findByCrm(crm);

        if (doctorFound == null) 
            return null;
        
        doctorFound.setName(doctorForm.name());
        doctorFound.setEmail(doctorForm.email());
        doctorFound.setCellphone(doctorForm.cellphone());
        doctorFound.setAddress(doctorForm.address());
        doctorFound.setSpecialty(doctorForm.specialty());
        doctorRepository.save(doctorFound);

        return new DoctorDTO(doctorFound);
    }

    public DoctorDTO deleteDoctor(String crm) {
        
        var deletedDoctor = doctorRepository.findByCrm(crm);

        if (deletedDoctor == null) {
            return null;
        }

        DoctorDTO deletedDoctorDTO = new DoctorDTO(deletedDoctor);
        doctorRepository.deleteById(crm);

        return deletedDoctorDTO;
    }
}
