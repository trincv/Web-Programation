package com.api.agenda.dtos;

import com.api.agenda.entities.Usuario;

public record UsuarioDTO(Long id, String username) {
	public UsuarioDTO(Usuario usuario) {
		this(usuario.getId(), usuario.getUsername());
	}

}
