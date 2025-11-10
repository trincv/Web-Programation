package ifba.edu.hospital.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ifba.edu.hospital.dtos.DoctorDTO;
import ifba.edu.hospital.dtos.DoctorFormDTO;
import ifba.edu.hospital.service.DoctorService;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorFormDTO doctor) {
        return ResponseEntity.ok(service.saveDoctor(doctor));
    }

    @GetMapping("/searchAll")
    public ResponseEntity<Page<DoctorDTO>> getAllDoctor(Pageable pageable) {

        Page<DoctorDTO> doctorPage = service.findAllDoctor(pageable);

        if (doctorPage.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(doctorPage);
    }
    
    @GetMapping("/searchByName")
    public ResponseEntity<List<DoctorDTO>> getDoctorByName(@RequestParam("name") String name) {

        List<DoctorDTO> doctorList = service.findDoctorByName(name);

        if (doctorList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(doctorList);
    }

    @PutMapping("/updateDoctor/{crm}")
    @Transactional
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable String crm, @RequestBody DoctorFormDTO doctorFormDTO) {
        
        var updatedDoctor = service.updateDoctor(crm, doctorFormDTO);

        if (updatedDoctor == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/deleteDoctor/{crm}")
    public ResponseEntity<DoctorDTO> deleteDoctor(@PathVariable String crm) {
        var deletedDoctor = service.deleteDoctor(crm);

        if (deletedDoctor == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(deletedDoctor);
    }

}
