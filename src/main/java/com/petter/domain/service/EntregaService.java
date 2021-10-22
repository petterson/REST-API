package com.petter.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.petter.domain.exception.CadastroClienteException;
import com.petter.domain.exception.EntidadeNaoEncontradaException;
import com.petter.domain.model.Cliente;
import com.petter.domain.model.Destinatario;
import com.petter.domain.model.Entrega;
import com.petter.domain.model.StatusEntrega;
import com.petter.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EntregaService {

	private ClienteService clienteService;
	private EntregaRepository entregaRepository;
	private DestinatarioService destinatarioService;

///////////////////LISTA TODOS AS ENTREGAS////////////////////////////////

	@Transactional
	public List<Entrega> listar() {
		return entregaRepository.findAll();
	}

///////////// BUSCA ENTREGA POR ID/////////////////////////////

	@Transactional
	public Optional<Entrega> findByEntregaPorId(Long entregaId) {
		return entregaRepository.findById(entregaId);
	}

///////////////// SALVA AS ENTREGAS////////////////////////////////////////

	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = clienteService.verificarCliente(entrega.getCliente().getId());
		Destinatario destinatario = destinatarioService.verificarDestinatario(entrega.getDestinatario().getId());
		entrega.setCliente(cliente);
		entrega.setDestinatario(destinatario);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());

		return entregaRepository.save(entrega);
	}

////////////////////////ATUALIZA A ENTREGA/////////////////////////

	@Transactional
	public Entrega atualizar(Entrega entrega) {
		return entregaRepository.save(entrega);
	}

/////////////////// VERIFICA ID ENTREGA/////////////////////////

	@Transactional
	public boolean existsById(Long clienteId) {
		return entregaRepository.existsById(clienteId);
	}

///////////////////////EXCLUI ENTREGA/////////////////////////////////

	@Transactional
	public void excluir(Long clienteId) {
		if (!this.existsById(clienteId)) {
			throw new CadastroClienteException("Não Existe Cliente com esse ID " + clienteId);
		}

		entregaRepository.deleteById(clienteId);
	}
	
	public Entrega buscaEntrega(Long entregaId) {
		return this.findByEntregaPorId(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não existe "));
		
	}
	
	@Transactional
	public void finalizarEntrega(Long entregaId) {	
		Entrega entrega = this.buscaEntrega(entregaId);
			if(!entrega.getStatus().equals(StatusEntrega.PENDENTE)) {
				throw new EntidadeNaoEncontradaException("Etrega não pode ser finalizada");
			}
			entrega.setStatus(StatusEntrega.FINALIZADA);
			entrega.setDataFinalizacao(OffsetDateTime.now());
		entregaRepository.save(entrega);
	}

}

