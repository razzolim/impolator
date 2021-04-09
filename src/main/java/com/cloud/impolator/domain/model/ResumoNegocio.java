package com.cloud.impolator.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ResumoNegocio {

	@Column(name = "resumo_negocio_debentures")
	private String debentures;
	
	@Column(name = "resumo_negocio_vendas_vista")
	private String vendasVista;
	
	@Column(name = "resumo_negocio_compras_vista")
	private String comprasVista;
	
	@Column(name = "resumo_negocio_opcoes_compras")
	private String opcoesCompras;
	
	@Column(name = "resumo_negocio_opcoes_vendas")
	private String opcoesVendas;
	
	@Column(name = "resumo_negocio_operacoes_termo")
	private String operacoesTermo;
	
	@Column(name = "resumo_negocio_valor_oper_ct_titulos_publ")
	private String valorOperCTitulosPubl;
	
	@Column(name = "resumo_negocio_valor_operacoes")
	private String valorOperacoes;
	
}
