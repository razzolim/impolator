package com.cloud.impolator.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

/**
 * @author Maicon Fang
*/

@Data
@Embeddable
public class CustoOperacional {
	
	@Column(name = "custo_operacional_taxa_operacional")
	private BigDecimal taxaOperacional;
	
	@Column(name = "custo_operacional_execucao")
	private BigDecimal execucao;
	
	@Column(name = "custo_operacional_taxa_custodia")
	private BigDecimal taxaCustodia;
	
	@Column(name = "custo_operacional_impostos")
	private BigDecimal impostos;
	
	@Column(name = "custo_operacional_irrf")
	private BigDecimal irrf;
	
	@Column(name = "custo_operacional_outros")
	private BigDecimal outros;
	
	@Column(name = "custo_operacional_total_custos_despesas")
	private BigDecimal totalCustosDespesas;
	
}
