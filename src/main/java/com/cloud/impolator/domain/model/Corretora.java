package com.cloud.impolator.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Corretora {

	@Column(name = "corretora_descricao", length=1000)
	private String descricao;

}
