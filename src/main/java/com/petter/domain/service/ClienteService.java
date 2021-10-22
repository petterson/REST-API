package com.petter.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petter.domain.exception.CadastroClienteException;
import com.petter.domain.model.Cliente;
import com.petter.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

///////////////////LISTA TODOS OS CLIENTES////////////////////////////////

	@Transactional
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

///////////// BUSCA CLIENTE POR ID/////////////////////////////

	@Transactional
	public Optional<Cliente> findByClientePorId(Long clienteId) {
		
		return clienteRepository.findById(clienteId);
	}
	
	
///////////// VERIFICA SE CLIENTE EXISTE/////////////////////////////
	
     @Transactional
     public Cliente verificarCliente(Long clienteId) {
     return clienteRepository.findById(clienteId)
		.orElseThrow(() -> new CadastroClienteException("Cliente Não Encontrado"));
     }
	

///////////////// SALVA OS CLIENTES////////////////////////////////////////

	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream()
				.anyMatch(funcionarioExistente -> !funcionarioExistente.equals(cliente));
		if (emailEmUso) {
		   throw new CadastroClienteException("Já existe Cliente com esse Email  " + cliente.getEmail());
		}
		return clienteRepository.save(cliente);
	}

//////////////////////// ATUALIZA O CLIENTE/////////////////////////

	@Transactional
	public Cliente atualizar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

/////////////////// VERIFICA ID CLIENTE/////////////////////////

	@Transactional
	public boolean existsById(Long clienteId) {
		return clienteRepository.existsById(clienteId);
	}

///////////////////////EXCLUI CLIENTE/////////////////////////////////

	@Transactional
	public void excluir(Long clienteId) {
		if (!this.existsById(clienteId)) {
			throw new CadastroClienteException("Não Existe Cliente com esse ID " + clienteId);
		}
		//Long l = pedidoRepository.findByIdDoCliente(clienteId);
		//if (l > 0L) {
		//	throw new CadastroClienteException("Esse cliente tem Restrição de Chave " + clienteId);
		//}
		clienteRepository.deleteById(clienteId);
	}

}

