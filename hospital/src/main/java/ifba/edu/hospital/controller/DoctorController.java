package ifba.edu.hospital.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.edu.hospital.entities.Doctor;
import ifba.edu.hospital.repository.DoctorRepository;
import ifba.edu.hospital.service.DoctorService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return service.saveDoctor(doctor);
    }

    @GetMapping
    public List<Doctor> getAllDoctor() {
        return service.findAllDoctor();
    }
    
    
}
