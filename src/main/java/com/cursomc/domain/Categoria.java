package com.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Categoria implements Serializable //convertidos para uma sequencia de byte, rede, arquivos
//exência do java
{
	
	//ATRIBUTOS BASICOS
	
	private static final long serialVersionUID = 1L; //serializer padrao
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //CUIDAR TIPO BANCO
	private Integer id;
	private String nome;

	@ManyToMany(mappedBy="categorias")
	private List<Produto> produtos = new ArrayList<>(); //associação

//construtor vazio -> Instancio o objeto sem jogar nada para os atributos

public Categoria() {
	}

//CONSTRUTOR COM OS ATRIBUTOS PARA ALIMENTAR OS DADOS DO CONSTRUTOR
//GERADOR CODIGO SOURCE GENERATOR -> COSNTRUTOR USER FIELDS


public Categoria(Integer id, String nome) {
	super();
	this.id = id;
	this.nome = nome;
}

//GETTERS E SETTERS POR PADRAO --> PADRAO PRIOVADO PARA NAO ACESSAR POR OUTRAS CLASSE
//USA ENTÃO O GETTES E SETTERS METODOS DE ACESSOS. SOURCE --> 

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

public List<Produto> getProdutos() {
	return produtos;
}

public void setProdutos(List<Produto> produtos) {
	this.produtos = produtos;

}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}





//HASCODE EQUALS --> COMPARADO PELO CONTEUDO METODOS DE COMPARAÇÃO POR VALOR

//SOURCE HASRCOD

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Categoria other = (Categoria) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;

}


}



