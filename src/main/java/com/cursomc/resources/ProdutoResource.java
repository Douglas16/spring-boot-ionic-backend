package com.cursomc.resources;






import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.domain.Produto;
import com.cursomc.dto.ProdutoDTO;
import com.cursomc.resources.utils.UtilURL;
import com.cursomc.services.ProdutoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET) //find id pois recebe um id na url
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException { //anotacao @pathvariable...
		Produto obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
				
				
	}
	
	@RequestMapping(method=RequestMethod.GET)

	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="categorias", defaultValue="") String categorias, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		String nomeDecoded = UtilURL.decodeParam(nome);
		
		                           

		List<Integer> ids = UtilURL.decodeIntList(categorias);
		//List<Integer> ids = URL.decodeIntList(categorias);

		Page<Produto> list = service.procura(nomeDecoded, ids, page, linesPerPage, orderBy, direction);

		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));  

		return ResponseEntity.ok().body(listDto);

	}

}
