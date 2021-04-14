package com.cloud.impolator.api.v1.model.input;

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
	private String debentures;
	
	@ApiModelProperty(example = "46,00", required = true)
	@NotBlank
	private String vendasVista;

	@ApiModelProperty(example = "45,00", required = true)
	@NotBlank
	private String comprasVista;
	
	@ApiModelProperty(example = "96,00", required = true)
	@NotBlank
	private String opcoesCompras;
	
	@ApiModelProperty(example = "35,00", required = true)
	@NotBlank
	private String opcoesVendas;
	
	@ApiModelProperty(example = "50,00", required = true)
	@NotBlank
	private String operacoesTermo;
	
	@ApiModelProperty(example = "9,00", required = true)
	@NotBlank
	private String valorOperCTitulosPubl;
	
	@ApiModelProperty(example = "70,00", required = true)
	@NotBlank
	private String valorOperacoes;
	
}
