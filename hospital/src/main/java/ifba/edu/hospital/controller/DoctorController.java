package ifba.edu.hospital.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.edu.hospital.dtos.doctor.DoctorDTO;
import ifba.edu.hospital.dtos.doctor.DoctorFormDTO;
import ifba.edu.hospital.dtos.doctor.DoctorUpdateForm;
import ifba.edu.hospital.dtos.login.LoginFormDTO;
import ifba.edu.hospital.service.DoctorService;
import ifba.edu.hospital.service.JWTokenService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<DoctorDTO> registerDoctor(@RequestBody @Valid DoctorFormDTO doctorForm) {
        return ResponseEntity.ok(doctorService.saveDoctor(doctorForm));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginDoctor(@RequestBody @Valid LoginFormDTO loginForm) {
        return ResponseEntity.ok(doctorService.loginDoctor(loginForm));
    }

    @GetMapping("/searchAll")
    public ResponseEntity<Page<DoctorDTO>> getAllDoctor(
        @PageableDefault(size = 10, sort = {"name"}, direction = Sort.Direction.ASC)
        Pageable pageable) 
    {
        var doctorPage = doctorService.findAllDoctor(pageable);

        if (doctorPage.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(doctorPage);
    }

    @PutMapping("/updateDoctor")
    public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody DoctorUpdateForm doctorUpdateForm) {
        
        var updatedDoctor = doctorService.updateDoctor(doctorUpdateForm);

        if (updatedDoctor == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/deleteDoctor")
    public ResponseEntity<DoctorDTO> deleteDoctor() {

        var deletedDoctor = doctorService.deleteDoctor();

        if (deletedDoctor == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(deletedDoctor);
    }

}
