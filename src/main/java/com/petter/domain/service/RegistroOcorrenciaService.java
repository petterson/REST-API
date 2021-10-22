package com.petter.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.petter.domain.exception.EntidadeNaoEncontradaException;
import com.petter.domain.model.Entrega;
import com.petter.domain.model.Ocorrencia;
import com.petter.domain.repository.OcorrenciaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	private OcorrenciaRepository ocorrenciaRepository;
	private EntregaService entregaService;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = entregaService.buscaEntrega(entregaId);
		return entrega.adicionarOcorrencia(descricao);
	}
	
	@Transactional
	public List<Ocorrencia> lista(){
		return ocorrenciaRepository.findAll();
	}
}

