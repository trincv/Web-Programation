package br.ifba.edu.blog.services;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.ifba.edu.blog.dtos.UsuarioDTO;
import br.ifba.edu.blog.dtos.UsuarioFormDTO;
import br.ifba.edu.blog.entities.Usuario;
import br.ifba.edu.blog.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;
	private PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Page<UsuarioDTO> getAllUsuarios(Pageable pageable) {
		// Lógica para obter todos os usuários
		return this.usuarioRepository.findAll(pageable).map(UsuarioDTO::new);
	}

	public UsuarioDTO createUsuario(UsuarioFormDTO usuario) {
		String senhaBcrypt=this.passwordEncoder.encode(usuario.senha());
		var usuarioEntity = new Usuario(usuario);
		usuarioEntity.setSenha(senhaBcrypt);
		var novoUsuario = this.usuarioRepository.save(usuarioEntity);
		return new UsuarioDTO(novoUsuario);
	}

	public List<UsuarioDTO> getUsuarioByNome(String nome) {

		List<UsuarioDTO> usuarios = this.usuarioRepository.findByNomeContaining(nome).stream().map(UsuarioDTO::new)
				.toList();
		if (usuarios.size() > 0) {
			return usuarios;
		} else {
			return null;
		}
	}
	
	
	public UsuarioDTO atualizarUsuario(Long id, UsuarioFormDTO usuario) {
		Usuario usuarioBanco = this.usuarioRepository.findById(id).orElse(null);
		if (usuarioBanco != null) {
			usuarioBanco.setNome(usuario.nome());
			usuarioBanco.setLogin(usuario.login());
			usuarioBanco.setSenha(usuario.senha());
			this.usuarioRepository.save(usuarioBanco);
		}
		return new UsuarioDTO(usuarioBanco);
	}
	
	public UsuarioDTO deleteUsuario(Long id) {
		// Lógica para deletar um usuário pelo ID
		Optional<Usuario> usuarioOp = this.usuarioRepository.findById(id);
		if (usuarioOp.isPresent()) {
			this.usuarioRepository.deleteById(id);
			return new UsuarioDTO(usuarioOp.get());
		}
		return null;
	}

}
