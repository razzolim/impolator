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
public class EspecificacaoDiversaInput {

	@ApiModelProperty(example = "A coluna Q indica liquidação no Agente do Qualificado", required = true)
	@NotBlank
	private String descricao;
	
	
}
