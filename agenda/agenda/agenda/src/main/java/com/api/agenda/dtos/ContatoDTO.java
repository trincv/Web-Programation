package com.api.agenda.dtos;

import java.util.List;

import com.api.agenda.entities.Contato;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ContatoDTO(// ID útil para operações de GET, PUT e DELETE
		Long id,

		@NotBlank(message = "O nome é obrigatório.") 
		@Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres.") 
		String nome,
		@NotBlank(message = "O sobrenome é obrigatório.") 
		@Size(min = 2, max = 150, message = "O nome deve ter entre 2 e 150 caracteres.")
		String sobrenome,

		@Email(message = "O email deve ser válido.") 
		String email,
		@Valid
		List<TelefoneDTO> telefones

) {

	public ContatoDTO(Contato contato) {
		this(contato.getId(), contato.getNome(), contato.getSobrenome(), contato.getEmail(),
				contato.getTelefones().stream().map(TelefoneDTO::new).toList());
	}
}
