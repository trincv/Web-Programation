package com.api.agenda.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.agenda.dtos.LoginDTO;
import com.api.agenda.dtos.UsuarioDTO;
import com.api.agenda.entities.Usuario;
import com.api.agenda.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;
	private PasswordEncoder passwordEncoder;
	
	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public UsuarioDTO cadastrarUsuario(LoginDTO loginDTO) {
	    var usuario = new Usuario(loginDTO);
	    // Encrypt to Bcrypt the password before saving
	    usuario.setPassword(passwordEncoder.encode(loginDTO.password()));
		usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
	}
	
	public UsuarioDTO apagarUsuario(Long id) {
		var usuario = usuarioRepository.getReferenceById(id);
		usuarioRepository.deleteById(id);
		return new UsuarioDTO(usuario);
	}
	
	public List<UsuarioDTO> listarTodos(){
		return usuarioRepository.findAll().stream().map(UsuarioDTO::new).toList();
	}
}
