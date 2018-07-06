package com.cursomc.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cursomc.domain.Categoria;
import com.cursomc.domain.Produto;

//BUSCA NO CAMPO COM ESSA INTERFACE E @IDENT
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Produto obj IINER JOIN obj.categorias cat WHERE obj.nome like %:nome% AND cat IN :categorias")
	//Page<Produto> procura(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias,  Pageable pageRequest);

	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias,
			PageRequest pageRequest);

}
