package com.cloud.impolator.api.v1.model;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemNotaModel extends RepresentationModel<ItemNotaModel> {

	@ApiModelProperty(example = "Q")
	private String q;
	
	@ApiModelProperty(example = "I-BOVESPA")
	private String negociacao;
	
	@ApiModelProperty(example = "C")
	private String cv;
	
	@ApiModelProperty(example = "FRACIONADO")
	private String tipoMercadoria;
	
	@ApiModelProperty(example = "30")
	private String prazo;
	
	@ApiModelProperty(example = "PETROBRAS")
	private String especificacaoTitulo;
	
	@ApiModelProperty(example = "D")
	private String obs;
	
	@ApiModelProperty(example = "100")
	private String quantidade;
	
	@ApiModelProperty(example = "16,42")
	private String precoAjuste;
	
	@ApiModelProperty(example = "700,52")
	@NotBlank
	private String valorOperacao;
	
	@ApiModelProperty(example = "D")
	private String dc;
}
