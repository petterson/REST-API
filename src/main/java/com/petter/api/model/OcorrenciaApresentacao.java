package com.petter.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaApresentacao {

	private Long id;
	private String descricao;
	private OffsetDateTime dataRegistro;
}
