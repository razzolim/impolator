package com.cloud.impolator.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

/**
 * @author Maicon Fang
 */

@Data
@Embeddable
public class Liquido {

	@Column(name = "liquido_data")
	private String data;
	
	@Column(name = "liquido_valor")
	private String valor;
	
	@Column(name = "liquido_observacao")
	private String observacao;
	
	
}
