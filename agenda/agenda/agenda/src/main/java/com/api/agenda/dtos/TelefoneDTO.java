package com.api.agenda.dtos;

import com.api.agenda.entities.CategoriaTelefone;
import com.api.agenda.entities.Telefone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TelefoneDTO(

		// ID do telefone. Útil para operações de atualização ou exclusão.
		Long id,

		@NotBlank(message = "O número do telefone é obrigatório.") 
		String numero,

		@NotNull(message = "A categoria do telefone é obrigatória.")
		// A categoria (PESSOAL, PROFISSIONAL, etc.) será esperada como uma String
		CategoriaTelefone categoria,

		// Indica se é o número principal do contato
		boolean principal) {

	public TelefoneDTO(Telefone telefone) {
		this(telefone.getId(), telefone.getNumero(), telefone.getCategoria(), telefone.getPrincipal());

	}
}