package com.petter.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.petter.domain.model.Ocorrencia;
import com.petter.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaApresentacao {
	
	private Long id;
	private ClienteApresentacao cliente;
	private DestinatarioApresentacao destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
	private List<Ocorrencia> ocorrencias;

}

