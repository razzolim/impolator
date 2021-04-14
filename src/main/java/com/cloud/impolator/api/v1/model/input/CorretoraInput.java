package com.cloud.impolator.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Maicon Fang
 */

@Setter
@Getter
public class CorretoraInput {

	@ApiModelProperty(example = "XP Investimento, com os dados da empresa", required = true)
	@NotBlank
	private String descricao;

}
