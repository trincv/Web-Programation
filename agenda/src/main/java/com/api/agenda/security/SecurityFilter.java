package com.api.agenda.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.agenda.repositories.UserRepository;
import com.api.agenda.services.JWTokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private JWTokenService tokenService;
    private UserRepository userRepository;

    public SecurityFilter(JWTokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        var token = recoverToken(request);

        if (token != null) {
            var subject = tokenService.validateToken(token);

            if (subject != null && !subject.isEmpty()) {
                var user = userRepository.findByUsername(subject);

                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }


    private String recoverToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");

        if (token == null) 
            return null;
        
        return token.replace("Bearer ", "");
    }
    
}
