package com.main.sistema_moedas.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity	
public class Instituicao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	private String nome;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private  List<Aluno> aluno;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private  List<Professor> professor;
	

}
