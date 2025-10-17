package br.ifba.edu.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifba.edu.blog.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByNome(String nome);

}
