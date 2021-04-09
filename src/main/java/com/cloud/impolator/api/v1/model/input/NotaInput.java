package com.cloud.impolator.api.v1.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotaInput {

	@ApiModelProperty(example = "1", required = true)
	private Long id;
	
	@Valid
	@NotNull
	private String numNota;

	@Valid
	@NotNull
	private String folha;
	
	@Valid
	@NotNull
	private String dataPregao;
	
	@Valid
	@Size(min = 1)
	@NotNull
	private List<ItemNotaInput> itens;
	
	@Valid
	@NotNull
	private CorretoraInput corretora;
	
	@Valid
	@NotNull
	private ResumoNegocioInput resumoNegocio;
	
	@Valid
	@NotNull
	private EspecificacaoDiversaInput especificacaoDiversa;
	
	@Valid
	@NotNull
	private ObservacaoInput observacao;
	
	@Valid
	@NotNull
	private ResumoFinanceiroInput resumoFinanceiro;
	
	@Valid
	@NotNull
	private CustoOperacionalInput custoOperacional;

	@Valid
	@NotNull
	private LiquidoInput liquido;

	
}
