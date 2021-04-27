package com.cloud.impolator.api.v1.model;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.cloud.impolator.api.v1.model.input.EspecificacaoDiversaInput;
import com.cloud.impolator.api.v1.model.input.ResumoNegocioInput;
import com.cloud.impolator.domain.model.Corretora;
import com.cloud.impolator.domain.model.CustoOperacional;
import com.cloud.impolator.domain.model.Liquido;
import com.cloud.impolator.domain.model.Observacao;
import com.cloud.impolator.domain.model.ResumoFinanceiro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Maicon Fang
*/

@Relation(collectionRelation = "notaNegociacao")
@Getter
@Setter
public class NotaModel extends RepresentationModel<NotaModel>{

	
	@ApiModelProperty(example = "6497158")
	private Integer numNota;

	@ApiModelProperty(example = "1")
	private Integer folha;
	
	@ApiModelProperty(example = "04/05/2020")
	private String dataPregao;
	
	private List<ItemNotaModel> itens;
	
	private Corretora corretora;
	
	private ResumoNegocioInput resumoNegocio;
	
	private EspecificacaoDiversaInput especificacaoDiversa;
	
	private Observacao observacao;
	
	private ResumoFinanceiro resumoFinanceiro;
	
	private CustoOperacional custoOperacional;
	
	private Liquido liquido;
	
}
