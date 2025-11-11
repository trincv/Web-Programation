package com.api.agenda.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.agenda.repositories.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	
	private UsuarioRepository repository;
	
	public AutenticacaoService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.findByUsername(username);
	}

}
