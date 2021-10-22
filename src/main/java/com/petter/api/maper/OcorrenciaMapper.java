package com.petter.api.maper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.petter.api.model.OcorrenciaApresentacao;
import com.petter.domain.model.Ocorrencia;
import com.petter.domain.service.RegistroOcorrenciaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {

	private ModelMapper modelMapper;
	
	public OcorrenciaApresentacao toModel(Ocorrencia ocorrencia) {
	return modelMapper.map(ocorrencia, OcorrenciaApresentacao.class);	
	}
	
	public List<OcorrenciaApresentacao> toCollectionModel(List<Ocorrencia> ocorrencias){
		return ocorrencias.stream().map(this::toModel).collect(Collectors.toList());
	}
}
