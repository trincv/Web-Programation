package br.ifba.edu.blog.config.security;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.ifba.edu.blog.repositories.UsuarioRepository;
import br.ifba.edu.blog.services.JWTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JWTokenService jwtTokenService;
	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {
		// Implement your security filtering logic here
		//System.out.println("SecurityFilter: CHAMADO aS "+ LocalDateTime.now());
		String token = recuperarToken(request);
		if(token!=null) {
			var login= jwtTokenService.getSubject(token);
			var usuario = usuarioRepository.findByLogin(login);
			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);

		}
		// Continue the filter chain
		filterChain.doFilter(request, response);
	}
	
	public String recuperarToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.replace("Bearer ", "");
    }


}
