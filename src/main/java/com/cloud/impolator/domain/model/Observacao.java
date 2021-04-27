package com.cloud.impolator.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

/**
 * @author Maicon Fang
 */

@Data
@Embeddable
public class Observacao {

	@Column(name = "observacao_descricao", length=1000)
	private String descricao;

}
