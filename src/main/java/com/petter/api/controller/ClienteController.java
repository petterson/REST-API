package com.petter.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.petter.domain.model.Cliente;
import com.petter.domain.service.ClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteService.listar();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return clienteService.findByClientePorId(clienteId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente c) {
	return this.clienteService.salvar(c);
	}
	  
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {
		if(!clienteService.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = clienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
		//return cr.findById(clienteId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	   
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> renover(@PathVariable Long clienteId) {
		if(!clienteService.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		clienteService.excluir(clienteId);
		return ResponseEntity.noContent().build();
		//return cr.findById(clienteId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

}
