package com.api.agenda.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.agenda.dtos.LoginFormDTO;
import com.api.agenda.dtos.user.UserDTO;
import com.api.agenda.dtos.user.UserFormDTO;
import com.api.agenda.entities.User;
import com.api.agenda.repositories.UserRepository;

@Service
public class UserService {
    
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JWTokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, JWTokenService tokenService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public String loginUser(LoginFormDTO loginForm) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(loginForm.username(), loginForm.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var login = (UserDetails) auth.getPrincipal();

        return this.tokenService.generateToken(login);
    }

    public UserDTO registerUser(UserFormDTO userForm) {

        var encryptedPassword = this.passwordEncoder.encode(userForm.password());

        var user = new User(userForm);
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        return new UserDTO(user);

    }
}
