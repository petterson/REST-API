package com.petter.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.petter.domain.model.Destinatario;
import com.petter.domain.service.DestinatarioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/destinatarios")
public class DestinatarioController {
	
	private DestinatarioService destinatarioService;
	
	@GetMapping
	public List<Destinatario> listar(){
		return destinatarioService.listar();
	}
	
	@GetMapping("/{destinatarioId}")
	public ResponseEntity<Destinatario> buscar(@PathVariable Long destinatarioId) {		
		return destinatarioService.findByDestinatarioPorId(destinatarioId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Destinatario adicionar(@Valid @RequestBody Destinatario destinatario) {
	return destinatarioService.salvar(destinatario);
	}
	
	@PutMapping("/{destinatarioId}")
	public ResponseEntity<Destinatario> atualizar(@PathVariable Long destinatarioId, @Valid @RequestBody Destinatario destinatario) {
		if(!destinatarioService.existsById(destinatarioId)) {
			return ResponseEntity.notFound().build();
		}
		destinatario.setId(destinatarioId);
		destinatario = destinatarioService.salvar(destinatario);
		return ResponseEntity.ok(destinatario);
	}
	   
	@DeleteMapping("/{destinatarioId}")
	public ResponseEntity<Void> renover(@PathVariable Long destinatarioId) {
		if(!destinatarioService.existsById(destinatarioId)) {
			return ResponseEntity.notFound().build();
		}
		destinatarioService.excluir(destinatarioId);
		return ResponseEntity.noContent().build();
		//return cr.findById(clienteId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

}

