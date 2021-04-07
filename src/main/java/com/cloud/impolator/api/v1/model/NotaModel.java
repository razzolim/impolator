package com.cloud.impolator.api.v1.model;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "notaNegociacao")
@Getter
@Setter
public class NotaModel extends RepresentationModel<NotaModel>{

	
	@ApiModelProperty(example = "6497158")
	private String numNota;

	@ApiModelProperty(example = "1")
	private String folha;
	
	@ApiModelProperty(example = "04/05/2020")
	private String dataPregao;
	
	// Dados da empresa
	@ApiModelProperty(example = "XP Investimentos")
	private String descricao;
	
	private List<ItemNotaModel> itens;
	
}
