package com.api.agenda.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.agenda.dtos.LoginDTO;
import com.api.agenda.dtos.TokenDTO;
import com.api.agenda.dtos.UsuarioDTO;
import com.api.agenda.entities.Usuario;
import com.api.agenda.services.JWTokenService;
import com.api.agenda.services.UsuarioService;

@RestController
@RequestMapping("/auth")
public class UsuarioController {
	
	private AuthenticationManager manager;
	private UsuarioService service;
	private JWTokenService tokenService;


	public UsuarioController(AuthenticationManager manager, UsuarioService service, JWTokenService tokenService) {
		this.manager = manager;
		this.service = service;
		this.tokenService = tokenService;
	}

	@PostMapping("/login")
	public ResponseEntity efetuarLogin(@RequestBody LoginDTO dadosLogin) {
		var token = new UsernamePasswordAuthenticationToken(dadosLogin.username(), dadosLogin.password());
		var authentication = manager.authenticate(token);
		var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
		return ResponseEntity.ok(new TokenDTO(tokenJWT));
	}
	
	@PostMapping("/register")
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody LoginDTO dadosLogin) {
		var usuario=service.cadastrarUsuario(dadosLogin);
		return ResponseEntity.ok(usuario);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UsuarioDTO> deletarUsuario(@PathVariable Long id) {
		var usuario=service.apagarUsuario(id);
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping
	public List<UsuarioDTO> listarUsuarios() {
		return service.listarTodos();
	}
	
	
	
}
