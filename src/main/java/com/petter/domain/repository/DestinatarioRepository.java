package com.petter.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petter.domain.model.Destinatario;

@Repository
public interface DestinatarioRepository extends JpaRepository<Destinatario, Long> {

	List<Destinatario> findByNome(String nome);
	//List<Destinatario> findByEmail(String email);
	List<Destinatario> findByNomeContaining(String nome);
}
