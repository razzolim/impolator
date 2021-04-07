package com.cloud.impolator.api.v1.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemNotaModel extends RepresentationModel<ItemNotaModel> {

	@ApiModelProperty(example = "Q")
	private String q;
	
	@ApiModelProperty(example = "Negociacao")
	private String negociacao;
	
	@ApiModelProperty(example = "cv")
	private String cv;
}
