package com.main.sistema_moedas.model;

import javax.persistence.Entity;

@Entity
public class Empresa extends Usuario {
  private String cnpj;

public String getCnpj() {
	return cnpj;
}

public void setCnpj(String cnpj) {
	this.cnpj = cnpj;
}
}
