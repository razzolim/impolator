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
public class ResumoNegocio {

	@Column(name = "resumo_negocio_debentures")
	private BigDecimal debentures;
	
	@Column(name = "resumo_negocio_vendas_vista")
	private BigDecimal vendasVista;
	
	@Column(name = "resumo_negocio_compras_vista")
	private BigDecimal comprasVista;
	
	@Column(name = "resumo_negocio_opcoes_compras")
	private BigDecimal opcoesCompras;
	
	@Column(name = "resumo_negocio_opcoes_vendas")
	private BigDecimal opcoesVendas;
	
	@Column(name = "resumo_negocio_operacoes_termo")
	private BigDecimal operacoesTermo;
	
	@Column(name = "resumo_negocio_valor_oper_ct_titulos_publ")
	private BigDecimal valorOperCTitulosPubl;
	
	@Column(name = "resumo_negocio_valor_operacoes")
	private BigDecimal valorOperacoes;
	
}
