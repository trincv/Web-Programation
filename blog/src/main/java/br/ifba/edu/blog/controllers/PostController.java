package br.ifba.edu.blog.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifba.edu.blog.dtos.PostDTO;
import br.ifba.edu.blog.dtos.PostFormDTO;
import br.ifba.edu.blog.services.PostService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public Page<PostDTO> listar(Pageable pageable){
		return this.postService.listar(pageable);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PostDTO> cadastrar(@RequestBody PostFormDTO postFormDTO){
		var postDTO = this.postService.cadastrar(postFormDTO);
		return ResponseEntity.ok(postDTO);
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PostDTO> atualizar(@PathVariable Long id, @RequestBody PostFormDTO postFormDTO){
		var postDTO = this.postService.atualizar(id, postFormDTO);
		return ResponseEntity.ok(postDTO);
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<PostDTO> apagarPost(@PathVariable Long id){
		var postDTO = this.postService.apagarPost(id);
		return ResponseEntity.ok(postDTO);
		
	}

}
