package com.cloud.impolator.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemNotaInput {

	@ApiModelProperty(example = "Q")
	private String q;
	
	@ApiModelProperty(example = "Negociacao")
	@NotBlank
	private String negociacao;
	
	@ApiModelProperty(example = "cv")
	private String cv;
	
}
