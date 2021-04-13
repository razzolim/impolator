package com.cloud.impolator.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemNota {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String q;
	
	private String negociacao;
	
	private String cv;
	
	private String tipoMercadoria;
	
	private String prazo;
	
	private String especificacaoTitulo;
	
	private String obs;
	
	private String quantidade;
	
	private String precoAjuste;
	
	private String valorOperacao;
	
	private String dc;
	
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Nota nota;
}
