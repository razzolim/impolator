package com.cloud.impolator.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Maicon Fang
*/

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemNota {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String q;
	
	private String bolsaValores;
	
	private Boolean compra;
	
	private String tipoMercado;
	
	private String prazo;
	
	private String especificacaoTitulo;
	
	private String obs;
	
	private Integer quantidade;
	
	private BigDecimal precoAjuste;
	
	private BigDecimal valorOperacao;
	
	private String debitoCredito;
	
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Nota nota;
}
