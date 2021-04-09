package com.cloud.impolator.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustoOperacionalInput {

	@ApiModelProperty(example = "10,00", required = true)
	@NotBlank
	private String taxaOperacional;
	
	@ApiModelProperty(example = "20,00", required = true)
	@NotBlank
	private String execucao;
	
	@ApiModelProperty(example = "30,00", required = true)
	@NotBlank
	private String taxaCustodia;
	
	@ApiModelProperty(example = "40,00", required = true)
	@NotBlank
	private String impostos;
	
	@ApiModelProperty(example = "50,00", required = true)
	@NotBlank
	private String irrf;
	
	@ApiModelProperty(example = "60,00", required = true)
	@NotBlank
	private String outros;
	
	@ApiModelProperty(example = "70,00", required = true)
	@NotBlank
	private String totalCustosDespesas;
	
}
