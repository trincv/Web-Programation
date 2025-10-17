package br.ifba.edu.blog.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ifba.edu.blog.entities.Usuario;
import br.ifba.edu.blog.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@GetMapping
	public List<Usuario> getAllUsuarios() {
		// Lógica para obter todos os usuários
		return this.usuarioRepository.findAll();
	}
	
	@GetMapping("/buscarPorNome")
	public Usuario getUsuarioByNome(@RequestParam String nome) {
		// Lógica para obter um usuário pelo nome
		return this.usuarioRepository.findByNome(nome);
	}
	
	@PostMapping
	public Usuario createUsuario(@RequestBody Usuario usuario) {
		this.usuarioRepository.save(usuario);
		return usuario;
	}
	
	@PutMapping("/{id}")
	public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		Usuario usuarioBanco = this.usuarioRepository.findById(id).orElse(null);
		if (usuarioBanco != null) {
			usuarioBanco.setNome(usuario.getNome());
			usuarioBanco.setLogin(usuario.getLogin());
			usuarioBanco.setSenha(usuario.getSenha());
			this.usuarioRepository.save(usuarioBanco);
		}
		return usuarioBanco;
	}
	
	@DeleteMapping("/{id}")
	public Usuario deleteUsuario(@PathVariable Long id) {
		// Lógica para deletar um usuário pelo ID
		Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
		if (usuario != null) {
			this.usuarioRepository.deleteById(id);
		}
		return usuario;
	}
	
	
}
