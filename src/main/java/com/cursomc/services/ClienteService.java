package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomc.domain.Cidade;
import com.cursomc.domain.Cliente;
import com.cursomc.domain.Endereco;
import com.cursomc.domain.enums.TipoCliente;
import com.cursomc.dto.ClienteDTO;
import com.cursomc.dto.ClienteNewDTO;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.repositories.EnderecoRepository;
import com.cursomc.services.exceptions.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {
	
	//OPERACAO BUSCAR Cliente POR CODIGO
	@Autowired //automaticamente instanciada pelo spring pelo Injeção de Depenencia
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente buscar(Integer id) throws ObjectNotFoundException   {
		
		Optional<Cliente> obj = repo.findById(id);
		//return obj.orElse(null);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
								"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
				
	}
	
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null); // atualização enao inserção
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
		
	}
	
	public Cliente update (Cliente obj) throws ObjectNotFoundException {
		
		//ver se existe o id
		//buscar(obj.getId());
		Cliente newObj = buscar(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) throws ObjectNotFoundException {
		//pra ver se o id existe
		buscar(id);
		
		try {
		
			repo.deleteById(id);
					
		}
		catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possível excluir uma cliente com pedidos");
		}
	}
	
	public List<Cliente> findAll() {
		
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPages, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction),	orderBy);
		return repo.findAll(pageRequest);
	}
	//METODO AUXILIAR
	public Cliente fromDTO(ClienteDTO objDto) {
		
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()) );
		
		Cidade cid = new Cidade(objDto.getCidadeId(), null,null);
		
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		
		
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		 if(objDto.getTelefone2()!=null) {
			 cli.getTelefones().add(objDto.getTelefone2());
		 }
		 if (objDto.getTelefone3()!=null) {
			 cli.getTelefones().add(objDto.getTelefone3());
		 }
		 return cli;
		
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}


}
