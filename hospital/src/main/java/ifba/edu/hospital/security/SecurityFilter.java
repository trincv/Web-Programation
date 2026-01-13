package ifba.edu.hospital.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ifba.edu.hospital.exception.ErrorResponse;
import ifba.edu.hospital.repository.LoginDataRepository;
import ifba.edu.hospital.service.JWTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    
    private final JWTokenService tokenService;
    private final LoginDataRepository loginDataRepository;

    public SecurityFilter(JWTokenService tokenService, LoginDataRepository loginDataRepository) {
        this.tokenService = tokenService;
        this.loginDataRepository = loginDataRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null) {
            var login = this.tokenService.validateToken(token);

            if (login != null && !login.isEmpty()) {
                var loginData = this.loginDataRepository.findByUserName(login);

                if (loginData != null) {
                    
                    if (!loginData.isEnabled()) {                             // Account's validation 
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("{\"status\": 403, \"message\": \"This account has been deactivated.\"}");
                        return;
                    }

                    var authentication = new UsernamePasswordAuthenticationToken(loginData, null, loginData.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } 
        }
        
        filterChain.doFilter(request, response);

    }
    
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null)
            return null;
        
        return authHeader.replace("Bearer ", "");
    }
}
