package ifba.edu.hospital.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ifba.edu.hospital.dtos.doctor.DoctorDTO;
import ifba.edu.hospital.dtos.doctor.DoctorFormDTO;
import ifba.edu.hospital.dtos.login.LoginFormDTO;
import ifba.edu.hospital.service.DoctorService;
import ifba.edu.hospital.service.JWTokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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

    private final DoctorService doctorService;
    private final JWTokenService tokenService;

    public DoctorController(DoctorService doctorService, JWTokenService tokenService) {
        this.doctorService = doctorService;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DoctorDTO> registerDoctor(@RequestBody @Valid DoctorFormDTO doctorForm) {
        return ResponseEntity.ok(doctorService.saveDoctor(doctorForm));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginDoctor(@RequestBody @Valid LoginFormDTO loginForm) {
        return ResponseEntity.ok(doctorService.loginDoctor(loginForm));
    }

    @GetMapping("/searchAll")
    public ResponseEntity<Page<DoctorDTO>> getAllDoctor(Pageable pageable) {

        Page<DoctorDTO> doctorPage = doctorService.findAllDoctor(pageable);

        if (doctorPage.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(doctorPage);
    }
    /* 
    @GetMapping("/searchByName")
    public ResponseEntity<List<DoctorDTO>> getDoctorByName(@RequestParam("name") String name) {

        List<DoctorDTO> doctorList = doctorService.findDoctorByName(name);

        if (doctorList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(doctorList);
    }

    @PutMapping("/updateDoctor/{crm}")
    @Transactional
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable String crm, @RequestBody DoctorFormDTO doctorFormDTO) {
        
        var updatedDoctor = doctorService.updateDoctor(crm, doctorFormDTO);

        if (updatedDoctor == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/deleteDoctor/{crm}")
    public ResponseEntity<DoctorDTO> deleteDoctor(@PathVariable String crm) {
        var deletedDoctor = doctorService.deleteDoctor(crm);

        if (deletedDoctor == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(deletedDoctor);
    }
    */
}
