package ifba.edu.hospital.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.edu.hospital.dtos.login.LoginFormDTO;
import ifba.edu.hospital.dtos.patient.PatientDTO;
import ifba.edu.hospital.dtos.patient.PatientFormDTO;
import ifba.edu.hospital.dtos.patient.PatientUpdateForm;
import ifba.edu.hospital.service.JWTokenService;
import ifba.edu.hospital.service.PatientService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;
    private final JWTokenService tokenService;

    public PatientController(PatientService patientService, JWTokenService tokenService) {
        this.tokenService = tokenService;
        this.patientService = patientService;
    }
    
    @GetMapping("/register")
    public ResponseEntity<PatientDTO> register(@RequestBody @Valid PatientFormDTO patientForm) {
        return ResponseEntity.ok(patientService.savePatient(patientForm));   
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginFormDTO loginForm) {
        return ResponseEntity.ok(patientService.loginPatient(loginForm));
    }
    
    @GetMapping("/searchAll")
    public ResponseEntity<Page<PatientDTO>> getAll(
        @PageableDefault(size = 10, sort = {"name"}, direction = Sort.Direction.ASC) 
        Pageable pageable) 
    {
        var patientPage = this.patientService.findAllPatient(pageable);

        if (patientPage.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(patientPage);
    }
    
    @PutMapping("update")
    public ResponseEntity<PatientDTO> update(@RequestBody @Valid PatientUpdateForm patientUpdateForm) {
        
        var updatePatient = this.patientService.updatePatient(patientUpdateForm);

        if(updatePatient == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatePatient);
    }

    @DeleteMapping("delete")
    public ResponseEntity<PatientDTO> delete() {

        var deletedPatient = this.patientService.deletePatient();

        if(deletedPatient == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(deletedPatient);
    }

}
