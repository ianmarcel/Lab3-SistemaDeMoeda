package com.main.sistema_moedas.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.main.sistema_moedas.model.usuario.Aluno;

@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int valor;
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Vantagem vantagem;
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Aluno aluno;

	public Compra(int valortotal, Aluno aluno, Vantagem vantagem) {
		this.setValor(valortotal);
		this.setAluno(aluno);
		this.setVantagem(vantagem);
	}

	public Vantagem getVantagem() {
		return vantagem;
	}

	public void setVantagem(Vantagem vantagem) {
		this.vantagem = vantagem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setValortotal(int valortotal) {
		this.valortotal = valortotal;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
