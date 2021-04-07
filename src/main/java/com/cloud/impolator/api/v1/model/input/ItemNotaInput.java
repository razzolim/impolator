package com.cloud.impolator.api.v1.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemNotaInput {

	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long id;
	
	@ApiModelProperty(example = "Q")
	private String q;
	
	@ApiModelProperty(example = "Negociacao")
	@NotBlank
	private String negociacao;
	
	@ApiModelProperty(example = "cv")
	private String cv;
	
}
