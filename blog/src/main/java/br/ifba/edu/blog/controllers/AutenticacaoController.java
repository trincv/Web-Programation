package br.ifba.edu.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifba.edu.blog.dtos.DadosAutenticacao;
import br.ifba.edu.blog.dtos.DadosTokenJWT;
import br.ifba.edu.blog.entities.Usuario;
import br.ifba.edu.blog.services.JWTokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	
	private AuthenticationManager manager;
	private JWTokenService tokenService;
	
	public AutenticacaoController(AuthenticationManager manager, JWTokenService tokenService) {
		this.manager = manager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody DadosAutenticacao dados) {
		var dto = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		var authentication = manager.authenticate(dto);
		var token = tokenService.gerarToken((Usuario) authentication.getPrincipal());
		return ResponseEntity.ok(new DadosTokenJWT(token));
	}

}
