package com.petter.api.maper;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.petter.api.model.EntregaApresentacao;
import com.petter.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaMapper {

	private ModelMapper modelMapper;
	
	public EntregaApresentacao toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaApresentacao.class);
	}
	
	public List<EntregaApresentacao> toCollectionModel(List<Entrega> entregas){
		return entregas.stream().map(this::toModel).collect(Collectors.toList());
	}
}
