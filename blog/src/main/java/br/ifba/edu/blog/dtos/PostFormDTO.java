package br.ifba.edu.blog.dtos;

import br.ifba.edu.blog.entities.Categoria;
import br.ifba.edu.blog.entities.Post;


public record PostFormDTO(String titulo, String texto, Long usuario, Categoria categoria) {
	
	public PostFormDTO(Post post) {
		this(post.getTitulo(), post.getTexto(), post.getUsuario().getId(), post.getCategoria());
	}

}
