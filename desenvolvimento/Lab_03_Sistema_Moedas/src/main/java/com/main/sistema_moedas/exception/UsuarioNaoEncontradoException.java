package com.main.sistema_moedas.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_STRING = "Usuário não encontrado";

	public UsuarioNaoEncontradoException() {
		super(MESSAGE_STRING);
	}

	
	
	

}
