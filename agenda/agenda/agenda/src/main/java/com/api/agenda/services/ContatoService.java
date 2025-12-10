package com.api.agenda.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.agenda.dtos.ContatoDTO;
import com.api.agenda.entities.Contato;
import com.api.agenda.entities.Telefone;
import com.api.agenda.entities.Usuario;
import com.api.agenda.repositories.ContatoRepository;
import com.api.agenda.repositories.UsuarioRepository;

@Service
public class ContatoService {
	private  ContatoRepository contatoRepository;
	private  UsuarioRepository usuarioRepository;
	
	public ContatoService(ContatoRepository contatoRepository, UsuarioRepository usuarioRepository) {
		this.contatoRepository = contatoRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	public ContatoDTO criarContato(ContatoDTO contatoDTO, String username) {
		var usuario = usuarioRepository.findByUsername(username);
		Contato novo=new Contato(contatoDTO,usuario);
		if (contatoDTO.telefones() != null) {
			contatoDTO.telefones().forEach(t -> {
				novo.getTelefones().add(new Telefone(t,novo));
			});
		}
		contatoRepository.save(novo);
		return new ContatoDTO(novo);	
	}
	
	public List<ContatoDTO> listarContatosPorUsuario(String username) {
		var usuario = (Usuario)usuarioRepository.findByUsername(username);
		var contatos = usuario.getContatos();
		return contatos.stream().map(ContatoDTO::new).toList();
	}
	
	public ContatoDTO obterContatoPorId(Long id) {
		var contato = contatoRepository.getReferenceById(id);
		return new ContatoDTO(contato);
	}
	
	public ContatoDTO atualizarContatoPorId(Long id, ContatoDTO contatoDTO, String username) {
		var usuario = (Usuario)usuarioRepository.findByUsername(username);
		var contato = contatoRepository.getReferenceById(id);
		if (!contato.getUsuario().getId().equals(usuario.getId())) {
			throw new RuntimeException("Contato não pertence ao usuário autenticado.");
		}
		contato.setNome(contatoDTO.nome());
		contato.setSobrenome(contatoDTO.sobrenome());
		contato.setEmail(contatoDTO.email());
		contatoRepository.save(contato);
		return new ContatoDTO(contato);
	}
	
	public ContatoDTO deletarContatoPorId(Long id) {
		var contato = contatoRepository.getReferenceById(id);
		contatoRepository.delete(contato);
		return new ContatoDTO(contato);
	}
	
}
