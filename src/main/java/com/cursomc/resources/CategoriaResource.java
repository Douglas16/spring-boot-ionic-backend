package com.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursomc.domain.Categoria;
import com.cursomc.services.CategoriaService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET) //find id pois recebe um id na url
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException { //anotacao @pathvariable...
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
				
		
	}
	
	//vai chamar um serviço
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		
		obj = service.insert(obj); //chama obj e inseri com novo id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
				
	}
@RequestMapping	(value="/{id}", method=RequestMethod.PUT)
public ResponseEntity<Void> update (@RequestBody Categoria obj, @PathVariable Integer id) throws ObjectNotFoundException {
	
	 obj.setId(id);
	 obj = service.update(obj);
	 
	 return ResponseEntity.noContent().build();
		
     }
}
