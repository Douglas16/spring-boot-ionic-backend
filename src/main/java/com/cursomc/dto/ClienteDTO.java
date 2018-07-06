package com.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cursomc.domain.Cliente;
import com.cursomc.services.validation.ClienteUpdate;
@ClienteUpdate
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Prenchimento obrigatório")
	@Length(min=5, max=120, message="Tamanho menor que 5 caracteres")
	private String nome;
	@NotEmpty(message="Prenchimento obrigatório")
	@Email(message="Email incorreto")
	private String email;
	
	public ClienteDTO() {
		
	}
	
	//CONSTRUTOR RECEBE ENTIDADE CLIENTE E GERA O DTO
	public ClienteDTO(Cliente obj) {
		
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
