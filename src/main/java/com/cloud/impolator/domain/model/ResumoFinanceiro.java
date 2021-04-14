package com.cloud.impolator.domain.model;

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
	private String valorLiquidoOperacoes;
	
	@Column(name = "resumo_financeiro_taxa_liquidacao")
	private String taxaLiquidacao;
	
	@Column(name = "resumo_financeiro_taxa_registro")
	private String taxaRegistro;
	
	@Column(name = "resumo_financeiro_total_cblc")
	private String totalcblc;
	
	@Column(name = "resumo_financeiro_taxa_termo_opcoes")
	private String taxaTermoOpcoes;
	
	@Column(name = "resumo_financeiro_taxa_ana")
	private String taxaAna;
	
	@Column(name = "resumo_financeiro_emolumentos")
	private String emolumentos;
	
	@Column(name = "resumo_financeiro_total_bovespa_soma")
	private String totalBovespaSoma;
	
}
