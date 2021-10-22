package com.petter.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.petter.api.maper.EntregaMapper;
import com.petter.api.model.EntregaApresentacao;
import com.petter.domain.model.Entrega;
import com.petter.domain.service.EntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	private EntregaService entregaService;
	private EntregaMapper entregaMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaApresentacao solicitar(@Valid @RequestBody Entrega entrega) {
		Entrega entregaSolicitar = entregaService.solicitar(entrega);
		return entregaMapper.toModel(entregaSolicitar);
	}
	
	@GetMapping
	public List<EntregaApresentacao> listar(){
		return entregaMapper.toCollectionModel(entregaService.listar());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaApresentacao> buscar(@PathVariable Long entregaId){
		return entregaService.findByEntregaPorId(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaMapper.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizarEntrega(@PathVariable Long entregaId) {
		entregaService.finalizarEntrega(entregaId);
	}

}

