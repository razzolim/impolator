package com.cloud.impolator.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LiquidoInput {

	@ApiModelProperty(example = "06/05/2020", required = true)
	@NotBlank
	private String data;
	
	@ApiModelProperty(example = "34,02", required = true)
	@NotBlank
	private String valor;
	
	@ApiModelProperty(example = "(1) As operaçõesatermo não são computadas no líquido dafatura.", required = true)
	@NotBlank
	private String observacao;
	
	
}
