package com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	//OPERACAO BUSCAR CATEGORIA POR CODIGO
	@Autowired //automaticamente instanciada pelo spring pelo Injeção de Depenencia
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
				
	}

}
