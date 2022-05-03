package com.main.sistema_moedas.model;

import javax.persistence.Entity;

@Entity
public class Professor {
	
	private String cpf;
	private String departamento;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

}
