package com.petter.domain.exception;

public class CadastroClienteException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	public CadastroClienteException(String mensagem) {
		super(mensagem);
		
	}

}
