package com.cloud.impolator.api.v1.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Maicon Fang
*/

@Getter
@Setter
public class ItemNotaModel extends RepresentationModel<ItemNotaModel> {

	@ApiModelProperty(example = "Q")
	private String q;
	
	@ApiModelProperty(example = "I-BOVESPA")
	private String bolsaValores;
	
	@ApiModelProperty(example = "C")
	private Boolean compra;
	
	@ApiModelProperty(example = "FRACIONADO")
	private String tipoMercado;
	
	@ApiModelProperty(example = "30")
	private String prazo;
	
	@ApiModelProperty(example = "PETROBRAS")
	private String especificacaoTitulo;
	
	@ApiModelProperty(example = "D")
	private String obs;
	
	@ApiModelProperty(example = "100")
	private Integer quantidade;
	
	@ApiModelProperty(example = "16,42")
	private BigDecimal precoAjuste;
	
	@ApiModelProperty(example = "700,52")
	@NotBlank
	private BigDecimal valorOperacao;
	
	@ApiModelProperty(example = "D")
	private String debitoCredito;
}
