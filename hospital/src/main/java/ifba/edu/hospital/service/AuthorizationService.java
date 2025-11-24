package ifba.edu.hospital.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ifba.edu.hospital.repository.LoginDataRepository;


@Service
public class AuthorizationService implements UserDetailsService {

    private final LoginDataRepository repository;

    public AuthorizationService(LoginDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserName(username); 
    }
}
    

