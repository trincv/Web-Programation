package ifba.edu.hospital.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserDetails loginData) {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        try {
            return JWT.create()
                      .withIssuer("api.doctor")
                      .withSubject(loginData.getUsername())
                      .withExpiresAt(expirationTime())
                      .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error at the creation of the JWT token" + exception);
        }
    }

    private Instant expirationTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token) {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        try {

            return JWT.require(algorithm)
                      .withIssuer("api.doctor")
                      .build()
                      .verify(token)
                      .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }
}
