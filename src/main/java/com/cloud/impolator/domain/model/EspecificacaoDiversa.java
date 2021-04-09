package com.cloud.impolator.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class EspecificacaoDiversa {

	@Column(name = "especificacao_diversa_descricao")
	private String descricao;
	
}
