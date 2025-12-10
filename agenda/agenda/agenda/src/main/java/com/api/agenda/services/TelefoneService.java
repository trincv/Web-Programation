package com.api.agenda.services;

import org.springframework.stereotype.Service;

import com.api.agenda.dtos.ContatoDTO;
import com.api.agenda.dtos.ListTelefoneDTO;
import com.api.agenda.dtos.TelefoneDTO;
import com.api.agenda.entities.Telefone;
import com.api.agenda.repositories.ContatoRepository;
import com.api.agenda.repositories.TelefoneRepository;

@Service
public class TelefoneService {

	private TelefoneRepository telefoneRepository;
	private ContatoRepository contatoRepository;

	public TelefoneService(TelefoneRepository telefoneRepository, ContatoRepository contatoRepository) {
		this.telefoneRepository = telefoneRepository;
		this.contatoRepository = contatoRepository;
	}

	public ContatoDTO cadastrarTelefones(Long contatoId, ListTelefoneDTO listaTelefonesDTO) {
		var contato = this.contatoRepository.getReferenceById(contatoId);
		if (!listaTelefonesDTO.numeros().isEmpty()) {
			this.telefoneRepository.saveAll(listaTelefonesDTO.numeros().stream()
					.map(telefoneDTO -> new Telefone(telefoneDTO, contato)).toList());
		}
		return new ContatoDTO(contato);
	}
	
	///contatos/{contatoId}/telefones/{telefoneId}
	public ContatoDTO deletarTelefone(Long contatoId, Long telefoneId) {
		var telefone = this.telefoneRepository.getReferenceById(telefoneId);
		if (telefone.getContato().getId().equals(contatoId)) {
			this.telefoneRepository.delete(telefone);
		} else {
			throw new RuntimeException("Telefone nao pertence ao contato");
		}
		var contato = this.contatoRepository.getReferenceById(contatoId);
		return new ContatoDTO(contato);
	}
	
	///contatos/{contatoId}/telefones/{telefoneId}
	public ContatoDTO atualizarTelefone(Long contatoId, Long telefoneId, TelefoneDTO novoNumero) {
		var telefone = this.telefoneRepository.getReferenceById(telefoneId);
		if (telefone.getContato().getId().equals(contatoId)) {
			telefone.setNumero(novoNumero.numero());
			telefone.setCategoria(novoNumero.categoria());
			telefone.setPrincipal(novoNumero.principal());
			this.telefoneRepository.save(telefone);
		} else {
			throw new RuntimeException("Telefone nao pertence ao contato");
		}
		var contato = this.contatoRepository.getReferenceById(contatoId);
		return new ContatoDTO(contato);
	}
}
