package ifba.edu.hospital.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import ifba.edu.hospital.dtos.login.LoginFormDTO;
import ifba.edu.hospital.dtos.patient.PatientDTO;
import ifba.edu.hospital.dtos.patient.PatientFormDTO;
import ifba.edu.hospital.entities.Address;
import ifba.edu.hospital.entities.LoginData;
import ifba.edu.hospital.entities.Patient;
import ifba.edu.hospital.enums.Roles;
import ifba.edu.hospital.repository.LoginDataRepository;
import ifba.edu.hospital.repository.PatientRepository;
import jakarta.transaction.Transactional;

@Service
public class PatientService {
    
    private final PatientRepository patientRepository;
    private final LoginDataRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public PatientService(PatientRepository patientRepository, LoginDataRepository loginRepository, 
                         JWTokenService tokenService, PasswordEncoder passwordEncoder,
                         AuthenticationManager authenticationManager) 
    {
        this.loginRepository = loginRepository;
        this.patientRepository = patientRepository;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public PatientDTO savePatient(PatientFormDTO patientForm) {

        var newPatient = new Patient(patientForm);
        var patientAddress = new Address(patientForm.address());
        var patientLogin = new LoginData(patientForm.userName(), this.passwordEncoder.encode(patientForm.password()), Roles.PATIENT);

        newPatient.setAddress(patientAddress);
        newPatient.setLogin(patientLogin);

        newPatient = this.patientRepository.save(newPatient);

        return new PatientDTO(newPatient);
    }

    public String loginPatient(LoginFormDTO loginForm) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(loginForm.userName(), loginForm.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var login = (UserDetails) auth.getPrincipal();

        return this.tokenService.generateToken(login);
    }

    public Page<PatientDTO> findAllPatient(Pageable pageable) {
        return this.patientRepository.findAllByLoginActiveTrue(pageable).map(PatientDTO::new);
    }
}
