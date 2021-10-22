package com.petter.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petter.domain.exception.CadastroClienteException;
import com.petter.domain.model.Destinatario;
import com.petter.domain.repository.DestinatarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DestinatarioService {

	private DestinatarioRepository destinatarioRepository;

///////////////////LISTA TODOS OS DESTINATARIOS////////////////////////////////

	@Transactional
	public List<Destinatario> listar() {
		return destinatarioRepository.findAll();
	}

///////////// BUSCA DESTINATARIO POR ID/////////////////////////////

	@Transactional
	public Optional<Destinatario> findByDestinatarioPorId(Long destinatarioId) {
		return destinatarioRepository.findById(destinatarioId);
	}

///////////// VERIFICA SE CLIENTE EXISTE/////////////////////////////

	@Transactional
	public Destinatario verificarDestinatario(Long destinatarioId) {
		return destinatarioRepository.findById(destinatarioId)
				.orElseThrow(() -> new CadastroClienteException("Destinatario Não Encontrado"));
	}

///////////////// SALVA OS DESTINATARIOS////////////////////////////////////////

	@Transactional
	public Destinatario salvar(Destinatario destinatario) {
		boolean nomeEmUso = destinatarioRepository.findByNome(destinatario.getNome()).stream()
				.anyMatch(destinatarioExistente -> !destinatarioExistente.equals(destinatario));
		if (nomeEmUso) {
			throw new CadastroClienteException("Já existe Cliente com esse Nome  " + destinatario.getNome());
		}
		return destinatarioRepository.save(destinatario);
	}

////////////////////////ATUALIZA O DESTINATARIO/////////////////////////

	@Transactional
	public Destinatario atualizar(Destinatario destinatario) {
		return destinatarioRepository.save(destinatario);
	}

/////////////////// VERIFICA ID DESTINATARIO/////////////////////////

	@Transactional
	public boolean existsById(Long clienteId) {
		return destinatarioRepository.existsById(clienteId);
	}

///////////////////////EXCLUI DESTINATARIO/////////////////////////////////

	@Transactional
	public void excluir(Long destinatarioId) {
		if (!this.existsById(destinatarioId)) {
			throw new CadastroClienteException("Não Existe Destinatario com esse ID " + destinatarioId);
		}

		destinatarioRepository.deleteById(destinatarioId);
	}
}

