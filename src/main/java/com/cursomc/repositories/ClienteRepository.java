package com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cursomc.domain.Cliente;

//BUSCA NO CAMPO COM ESSA INTERFACE E @IDENT
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Transactional(readOnly=true) //mais rapido e diminio o lock de banco
	Cliente findByEmail(String email);

}

