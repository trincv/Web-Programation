package ifba.edu.hospital.service;

import java.util.List;

import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ifba.edu.hospital.dtos.doctor.DoctorDTO;
import ifba.edu.hospital.dtos.doctor.DoctorFormDTO;
import ifba.edu.hospital.dtos.login.LoginFormDTO;
import ifba.edu.hospital.entities.Address;
import ifba.edu.hospital.entities.Doctor;
import ifba.edu.hospital.entities.LoginData;
import ifba.edu.hospital.enums.Roles;
import ifba.edu.hospital.repository.DoctorRepository;
import ifba.edu.hospital.repository.LoginDataRepository;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final LoginDataRepository loginDataRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public DoctorService(DoctorRepository doctorRepository, PasswordEncoder passwordEncoder, 
                        JWTokenService tokenService, LoginDataRepository loginDataRepository,
                        AuthenticationManager authenticationManager) 
    {
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.loginDataRepository = loginDataRepository;
        this.authenticationManager = authenticationManager;
    }

    public DoctorDTO saveDoctor(DoctorFormDTO doctorForm) {

        var newDoctor = new Doctor(doctorForm);
        var address = new Address(doctorForm.address());
        var doctorLogin = new LoginData(doctorForm.userName(), passwordEncoder.encode(doctorForm.password()), Roles.DOCTOR);

        newDoctor.setAddress(address);
        newDoctor.setLogin(doctorLogin);

        var savedDoctor = doctorRepository.save(newDoctor);

        return new DoctorDTO(savedDoctor);
    }

    public String loginDoctor(LoginFormDTO loginForm) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(loginForm.userName(), loginForm.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        var login = (UserDetails) auth.getPrincipal();

        return this.tokenService.generateToken(login);
    }

    public Page<DoctorDTO> findAllDoctor(Pageable pageable) {
        return doctorRepository.findAll(pageable).map(DoctorDTO::new);
    }
    /* 
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
        */
}
