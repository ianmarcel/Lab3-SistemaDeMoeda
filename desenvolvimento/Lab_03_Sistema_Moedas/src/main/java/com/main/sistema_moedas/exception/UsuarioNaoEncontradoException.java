package com.main.sistema_moedas.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsuarioNaoEncontradoException extends UsernameNotFoundException {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_STRING = "Usuário não encontrado";

	public UsuarioNaoEncontradoException() {
		super(MESSAGE_STRING);
	}

}
