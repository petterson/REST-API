package com.petter.domain.exception;

public class EntidadeNaoEncontradaException extends CadastroClienteException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

}

