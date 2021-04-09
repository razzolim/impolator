package com.cloud.impolator.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class CustoOperacional {
	
	@Column(name = "custo_operacional_taxa_operacional")
	private String taxaOperacional;
	
	@Column(name = "custo_operacional_execucao")
	private String execucao;
	
	@Column(name = "custo_operacional_taxa_custodia")
	private String taxaCustodia;
	
	@Column(name = "custo_operacional_impostos")
	private String impostos;
	
	@Column(name = "custo_operacional_irrf")
	private String irrf;
	
	@Column(name = "custo_operacional_outros")
	private String outros;
	
	@Column(name = "custo_operacional_total_custos_despesas")
	private String totalCustosDespesas;
	
}
