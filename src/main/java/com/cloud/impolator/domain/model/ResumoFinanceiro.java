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
public class ResumoFinanceiro {
	
	@Column(name = "resumo_financeiro_valor_liquido_operacoes")
	private BigDecimal valorLiquidoOperacoes;
	
	@Column(name = "resumo_financeiro_taxa_liquidacao")
	private BigDecimal taxaLiquidacao;
	
	@Column(name = "resumo_financeiro_taxa_registro")
	private BigDecimal taxaRegistro;
	
	@Column(name = "resumo_financeiro_total_cblc")
	private BigDecimal totalcblc;
	
	@Column(name = "resumo_financeiro_taxa_termo_opcoes")
	private BigDecimal taxaTermoOpcoes;
	
	@Column(name = "resumo_financeiro_taxa_ana")
	private BigDecimal taxaAna;
	
	@Column(name = "resumo_financeiro_emolumentos")
	private BigDecimal emolumentos;
	
	@Column(name = "resumo_financeiro_total_bovespa_soma")
	private BigDecimal totalBovespaSoma;
	
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
	
	@Column(name = "custo_operacional_liquidoPara")
	private BigDecimal liquidoPara;
	
}
