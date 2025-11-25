package com.api.agenda.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.api.agenda.dtos.LoginFormDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JWTokenService {

    @Value("${jwt.secret}")
    private String value;

    public String generateToken(UserDetails login) {

        Algorithm algorithm = Algorithm.HMAC256(value);

        try {
            return JWT.create()
            .withIssuer("API.Agenda")
            .withSubject(login.getUsername())
            .withExpiresAt(expirationDateToken()) 
            .sign(algorithm);

        } catch (Exception e) {
            throw new RuntimeException("Erro at the creation of token" + e.getMessage());
        }
    }

    private Instant expirationDateToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3:00"));
    }

    public String validateToken(String token) {

        Algorithm algorithm = Algorithm.HMAC256(value);

        try {
            return JWT.require(algorithm)
            .withIssuer("API.Agenda")
            .build()
            .verify(token)
            .getSubject();
            
        } catch (Exception e) {
            return "";
        }
    }
}
