package com.petter.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.petter.api.maper.OcorrenciaMapper;
import com.petter.api.model.EntregaApresentacao;
import com.petter.api.model.OcorrenciaApresentacao;
import com.petter.domain.model.Entrega;
import com.petter.domain.model.Ocorrencia;
import com.petter.domain.service.EntregaService;
import com.petter.domain.service.RegistroOcorrenciaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	private RegistroOcorrenciaService registroOcorrenciaService;
	private EntregaService entregaService;
	private OcorrenciaMapper ocorrenciaMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaApresentacao registrar(@PathVariable Long entregaId, @Valid @RequestBody Ocorrencia ocorrencia) {
		Ocorrencia ocorrenciaRegistrar = registroOcorrenciaService.registrar(entregaId, ocorrencia.getDescricao());
		return ocorrenciaMapper.toModel(ocorrenciaRegistrar);
	}
	
	@GetMapping
	public List<OcorrenciaApresentacao> listar(@PathVariable Long entregaId){
		Entrega entrega = entregaService.buscaEntrega(entregaId);
		return ocorrenciaMapper.toCollectionModel(entrega.getOcorrecias());
	}
}

