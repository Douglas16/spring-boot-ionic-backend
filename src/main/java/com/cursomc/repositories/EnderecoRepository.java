package com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursomc.domain.Endereco;

//BUSCA NO CAMPO COM ESSA INTERFACE E @IDENT
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
