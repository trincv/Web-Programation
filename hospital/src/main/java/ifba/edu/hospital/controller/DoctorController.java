package ifba.edu.hospital.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.edu.hospital.entities.Doctor;
import ifba.edu.hospital.service.DoctorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/Doctor")
@RestController
public class DoctorController {

    private final DoctorService service = new DoctorService();

    @PostMapping(produces = "application/json")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {

        Doctor createDoctor = service.createDoctor(doctor);

        return new ResponseEntity<>(createDoctor, HttpStatus.CREATED);
    }
    
}
