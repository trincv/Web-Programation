package br.ifba.edu.blog.entities;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity(name = "roles")
public class Role implements GrantedAuthority {

	private String authority;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Role(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Role(String authority, Long id) {
		super();
		this.authority = authority;
		this.id = id;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
