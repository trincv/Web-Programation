package br.ifba.edu.blog.services;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifba.edu.blog.dtos.PostDTO;
import br.ifba.edu.blog.dtos.PostFormDTO;
import br.ifba.edu.blog.entities.Post;
import br.ifba.edu.blog.repositories.PostRepository;
import br.ifba.edu.blog.repositories.UsuarioRepository;

@Service
public class PostService {

	private PostRepository postRepository;
	private UsuarioRepository usuarioRepository;
	
	public PostService(PostRepository postRepository, UsuarioRepository usuarioRepository) {
		this.postRepository = postRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	public Page<PostDTO> listar(Pageable pageable){
		return this.postRepository.findAll(pageable).map(PostDTO::new);
	}
	
	public PostDTO cadastrar(PostFormDTO postFormDTO) {
		var usuario = this.usuarioRepository.getReferenceById(postFormDTO.usuario());
		
			var post=new Post(postFormDTO);
			post.setUsuario(usuario);
			this.postRepository.save(post);
			return new PostDTO(post);
		
	}
	
	
	public PostDTO atualizar(Long id, PostFormDTO postFormDTO) {
		var post = this.postRepository.getReferenceById(id);
		var usuario = this.usuarioRepository.getReferenceById(postFormDTO.usuario());
			post.setTitulo( postFormDTO.titulo());
			post.setTexto( postFormDTO.texto());
			post.setUsuario(usuario);
			post.setCategoria( postFormDTO.categoria());
			this.postRepository.save(post);
			return new PostDTO(post);
		
	}
	
	public PostDTO apagarPost(Long id) {
		var post = this.postRepository.getReferenceById(id);
		
			this.postRepository.deleteById(id);
			return new PostDTO(post);
		
	}
}