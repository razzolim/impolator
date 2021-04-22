package com.cloud.impolator.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Maicon Fang
*/

@Getter
@Setter
public class ItemNotaInput {

	@ApiModelProperty(example = "Q")
	private String q;
	
	@ApiModelProperty(example = "I-BOVESPA")
	@NotBlank
	private String negociacao;
	
	@ApiModelProperty(example = "C")
	@NotBlank
	private Boolean compra;
	
	@ApiModelProperty(example = "FRACIONADO")
	@NotBlank
	private String tipoMercadoria;
	
	@ApiModelProperty(example = "30")
	private String prazo;
	
	@ApiModelProperty(example = "PETROBRAS")
	@NotBlank
	private String especificacaoTitulo;
	
	@ApiModelProperty(example = "D")
	private String obs;
	
	@ApiModelProperty(example = "100")
	@NotBlank
	private Integer quantidade;
	
	@ApiModelProperty(example = "16,42")
	@NotBlank
	private BigDecimal precoAjuste;
	
	@ApiModelProperty(example = "700,52")
	@NotBlank
	private BigDecimal valorOperacao;
	
	@ApiModelProperty(example = "D")
	@NotBlank
	private String dc;
	
}
