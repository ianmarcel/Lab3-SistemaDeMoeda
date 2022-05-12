package com.main.sistema_moedas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int valor;
	private String descricao;
	@Column(unique = true)
	private Conta origem;
	@Column(unique = true)
	private Conta destino;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Conta getOrigem() {
		return origem;
	}
	public void setOrigem(Conta origem) {
		this.origem = origem;
	}
	public Conta getDestino() {
		return destino;
	}
	public void setDestino(Conta destino) {
		this.destino = destino;
	}
	
	
	
	
	

}
