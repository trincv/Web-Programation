package com.api.agenda.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.agenda.dtos.LoginFormDTO;
import com.api.agenda.dtos.user.UserDTO;
import com.api.agenda.dtos.user.UserFormDTO;
import com.api.agenda.services.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody @Valid LoginFormDTO login) {

        return ResponseEntity.ok(userService.loginUser(login));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserDTO> userRegister(@RequestBody @Valid UserFormDTO userForm) {

        return ResponseEntity.ok(userService.registerUser(userForm));
    }
    
}
