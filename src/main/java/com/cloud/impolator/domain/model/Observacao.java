package com.cloud.impolator.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Observacao {

	@Column(name = "observacao_descricao")
	private String descricao;

}
