package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.domain.Produto;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.repositories.ProdutoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	//OPERACAO BUSCAR CATEGORIA POR CODIGO
	@Autowired //automaticamente instanciada pelo spring pelo Injeção de Depenencia
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto buscar(Integer id) throws ObjectNotFoundException   {
		
		Optional<Produto> obj = repo.findById(id);
		//return obj.orElse(null);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
								"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
				
	}
	
	//OPERACAO DE PROCURA
	
	public Page<Produto> procura(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		List<Categoria> categorias = categoriaRepository.findAllById(ids);

		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
		
	}
}
