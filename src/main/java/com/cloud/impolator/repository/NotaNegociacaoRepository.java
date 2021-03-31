package com.cloud.impolator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.impolator.entity.NotaNegociacao;

public interface NotaNegociacaoRepository extends JpaRepository<NotaNegociacao, Long> {

	public List<NotaNegociacao> findByOrderByNumNotaAsc();

}
