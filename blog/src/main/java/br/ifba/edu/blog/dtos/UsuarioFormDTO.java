package br.ifba.edu.blog.dtos;

import br.ifba.edu.blog.entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public record UsuarioFormDTO(
		@NotBlank(message = "O nome não pode ser nulo")
		@Schema(description = "Nome do usuário", example = "João Silva")
		String nome,
		@NotBlank(message = "O login não pode ser nulo")
		@Schema(description = "Login do usuário", example = "joaosilva@gmail.com")
		String login, 
		@NotBlank(message = "A senha não pode ser nulo")
		@Schema(description = "Senha do usuário", example = "senha123")
		String senha) {
	
	public UsuarioFormDTO(Usuario usuario) {
		this(usuario.getNome(), usuario.getLogin(), usuario.getSenha());
	}

}
