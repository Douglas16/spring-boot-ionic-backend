package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.dto.CategoriaDTO;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.services.exceptions.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	//OPERACAO BUSCAR CATEGORIA POR CODIGO
	@Autowired //automaticamente instanciada pelo spring pelo Injeção de Depenencia
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) throws ObjectNotFoundException   {
		
		Optional<Categoria> obj = repo.findById(id);
		//return obj.orElse(null);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
								"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
				
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); // atualização enao inserção
		return repo.save(obj);
	}
	
	public Categoria update (Categoria obj) throws ObjectNotFoundException {
		
		//ver se existe o id
		buscar(obj.getId());
		
		return repo.save(obj);
	}
	
	public void delete(Integer id) throws ObjectNotFoundException {
		//pra ver se o id existe
		buscar(id);
		
		try {
		
			repo.deleteById(id);
					
		}
		catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public List<Categoria> findAll() {
		
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPages, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction),	orderBy);
		return repo.findAll(pageRequest);
	}
	//METODO AUXILIAR
	public Categoria fromDTO(CategoriaDTO objDto) {
		
		return new Categoria(objDto.getId(), objDto.getNome());
	}

}
