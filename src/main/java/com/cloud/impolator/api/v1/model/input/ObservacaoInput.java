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
public class ObservacaoInput {

	@ApiModelProperty(example = "8 - Liquidação Institucional; D- Day Trade; I - POP ", required = true)
	@NotBlank
	private String descricao;
	
}
