package com.cloud.impolator.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Maicon Fang
*/

@Getter
@Setter
public class ResumoFinanceiroInput {

	@ApiModelProperty(example = "10,00", required = true)
	@NotBlank
	private String valorLiquidoOperacoes;
	
	@ApiModelProperty(example = "20,00", required = true)
	@NotBlank
	private String taxaLiquidacao;
	
	@ApiModelProperty(example = "30,00", required = true)
	@NotBlank
	private String taxaRegistro;
	
	@ApiModelProperty(example = "40,00", required = true)
	@NotBlank
	private String totalcblc;
	
	@ApiModelProperty(example = "50,00", required = true)
	@NotBlank
	private String taxaTermoOpcoes;
	
	@ApiModelProperty(example = "60,00", required = true)
	@NotBlank
	private String taxaAna;
	
	@ApiModelProperty(example = "70,00", required = true)
	@NotBlank
	private String emolumentos;
	
	@ApiModelProperty(example = "80,00", required = true)
	@NotBlank
	private String totalBovespaSoma;
	
	@ApiModelProperty(example = "10,00", required = true)
	@NotBlank
	private BigDecimal taxaOperacional;
	
	@ApiModelProperty(example = "20,00", required = true)
	@NotBlank
	private BigDecimal execucao;
	
	@ApiModelProperty(example = "30,00", required = true)
	@NotBlank
	private BigDecimal taxaCustodia;
	
	@ApiModelProperty(example = "40,00", required = true)
	@NotBlank
	private BigDecimal impostos;
	
	@ApiModelProperty(example = "50,00", required = true)
	@NotBlank
	private BigDecimal irrf;
	
	@ApiModelProperty(example = "60,00", required = true)
	@NotBlank
	private BigDecimal outros;
	
	@ApiModelProperty(example = "70,00", required = true)
	@NotBlank
	private BigDecimal totalCustosDespesas;
	
	@ApiModelProperty(example = "34,02,00", required = true)
	@NotBlank
	private BigDecimal liquidoPara;
	
}
