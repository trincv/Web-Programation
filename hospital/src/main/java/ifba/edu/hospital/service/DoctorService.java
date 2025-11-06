package ifba.edu.hospital.service;

import java.util.ArrayList;
import java.util.List;

import ifba.edu.hospital.entities.Doctor;

public class DoctorService {

    private final List<Doctor> doctors = new ArrayList<>();

    public Doctor createDoctor(Doctor doctor) {
        
        doctors.add(doctor);
        
        return doctor; 
    }

}
