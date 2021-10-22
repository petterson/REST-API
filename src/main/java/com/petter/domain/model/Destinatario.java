package com.petter.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.petter.domain.ValidationGroups;

//import com.petter.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Destinatario {

	@NotNull(groups = ValidationGroups.DestinatarioId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max=70)
	private String nome;
	
	@NotBlank
	@Size(max=70)
	private String logradouro;
	
	@NotBlank
	@Size(max=10)
	private String numero;
	
	@NotBlank
	@Size(max=70)
	private String complemento;
	
	@NotBlank
	@Size(max=70)
	private String bairro;
	
}

