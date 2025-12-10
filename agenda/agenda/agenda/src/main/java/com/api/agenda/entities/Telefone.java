package com.api.agenda.entities;

import com.api.agenda.dtos.TelefoneDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "telefones")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numero;

    // Mapeia o Enum para o banco de dados como uma String
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaTelefone categoria;

    @Column(name = "is_principal", nullable = false)
    private Boolean principal = false;

    // Relacionamento: Muitos Telefones pertencem a um único Contato
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contato_id", nullable = false)
    private Contato contato; // Chave estrangeira para a tabela contatos
    
    
    

	public Telefone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Telefone(TelefoneDTO telefoneDTO, Contato contato) {
		this.numero = telefoneDTO.numero();
		this.categoria = telefoneDTO.categoria();
		this.principal = telefoneDTO.principal();
		this.contato = contato;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public CategoriaTelefone getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaTelefone categoria) {
		this.categoria = categoria;
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
    
  
    
}