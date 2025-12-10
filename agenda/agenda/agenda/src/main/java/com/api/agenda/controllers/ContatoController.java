package com.api.agenda.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.agenda.dtos.ContatoDTO;
import com.api.agenda.dtos.ListTelefoneDTO;
import com.api.agenda.dtos.TelefoneDTO;
import com.api.agenda.services.ContatoService;
import com.api.agenda.services.TelefoneService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

	private ContatoService contatoService;
	private TelefoneService telefoneService;
	
	public ContatoController(ContatoService contatoService, TelefoneService telefoneService) {
		this.contatoService = contatoService;
		this.telefoneService = telefoneService;
	}
	
	@PostMapping
	public ResponseEntity<ContatoDTO> criarContato(@RequestBody @Valid ContatoDTO contatoDTO) {
	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    ContatoDTO novoContato = contatoService.criarContato(contatoDTO, username);
	    // Retorna a resposta com o status 201 Created e o corpo contendo o novo contato
	    return ResponseEntity.status(201).body(novoContato);

	}
	
	@GetMapping
	public ResponseEntity<List<ContatoDTO>> listarContatos() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<ContatoDTO> contatos = contatoService.listarContatosPorUsuario(username);
		return ResponseEntity.ok(contatos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContatoDTO> obterContatoPorId(@PathVariable Long id) {
		ContatoDTO contato = contatoService.obterContatoPorId(id);
		return ResponseEntity.ok(contato);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ContatoDTO> atualizarContatoPorId(@PathVariable Long id, @RequestBody @Valid ContatoDTO contatoDTO) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		ContatoDTO contatoAtualizado = contatoService.atualizarContatoPorId(id, contatoDTO, username);
		return ResponseEntity.ok(contatoAtualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ContatoDTO> deletarContatoPorId(@PathVariable Long id) {
		ContatoDTO contatoDeletado = contatoService.deletarContatoPorId(id);
		return ResponseEntity.ok(contatoDeletado);
	}
	
	///contatos/{contatoId}/telefones Add lista de um ou mais telefones a um contato existente
	@PostMapping("/{contatoId}/telefones")
	public ResponseEntity<ContatoDTO> adicionarTelefonesAoContato(@PathVariable Long contatoId,
			@RequestBody ListTelefoneDTO telefones) {
		ContatoDTO contatoAtualizado = telefoneService.cadastrarTelefones(contatoId, telefones);
		return ResponseEntity.ok(contatoAtualizado);
	}
	///contatos/{contatoId}/telefones/{telefoneId} atualizar um telefone de um contato existente
	@PutMapping("/{contatoId}/telefones/{telefoneId}")
	public ResponseEntity<ContatoDTO> atualizarTelefoneDoContato(@PathVariable Long contatoId,
			@PathVariable Long telefoneId, @RequestBody @Valid TelefoneDTO novoNumero) {
		ContatoDTO contatoAtualizado = telefoneService.atualizarTelefone(contatoId, telefoneId,
				novoNumero);
		return ResponseEntity.ok(contatoAtualizado);
	}
	
	///contatos/{contatoId}/telefones/{telefoneId} apagar um telefone de um contato existente
	@DeleteMapping("/{contatoId}/telefones/{telefoneId}")
	public ResponseEntity<ContatoDTO> deletarTelefoneDoContato(@PathVariable Long contatoId,
			@PathVariable Long telefoneId) {
		ContatoDTO contatoAtualizado = telefoneService.deletarTelefone(contatoId, telefoneId);
		return ResponseEntity.ok(contatoAtualizado);
	}
}
