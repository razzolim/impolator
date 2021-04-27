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
public class ResumoNegocioInput {

	@ApiModelProperty(example = "10,00", required = true)
	@NotBlank
	private BigDecimal debentures;
	
	@ApiModelProperty(example = "46,00", required = true)
	@NotBlank
	private BigDecimal vendasVista;

	@ApiModelProperty(example = "45,00", required = true)
	@NotBlank
	private BigDecimal comprasVista;
	
	@ApiModelProperty(example = "96,00", required = true)
	@NotBlank
	private BigDecimal opcoesCompras;
	
	@ApiModelProperty(example = "35,00", required = true)
	@NotBlank
	private BigDecimal opcoesVendas;
	
	@ApiModelProperty(example = "50,00", required = true)
	@NotBlank
	private BigDecimal operacoesTermo;
	
	@ApiModelProperty(example = "9,00", required = true)
	@NotBlank
	private BigDecimal valorOperCTitulosPubl;
	
	@ApiModelProperty(example = "70,00", required = true)
	@NotBlank
	private BigDecimal valorOperacoes;
	
}
