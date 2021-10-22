package com.petter.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioApresentacao {
	
	private Long id;
	private String nome;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;

}
