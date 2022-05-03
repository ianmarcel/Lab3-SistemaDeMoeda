package com.main.sistema_moedas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conta {
    
	private int saldo;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
  
  public String consultarExtrato() {
	  return null;
  }
 
}
