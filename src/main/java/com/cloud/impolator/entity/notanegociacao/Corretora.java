package com.cloud.impolator.entity.notanegociacao;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Maicon Fang
 * 
 * */

@Getter
@Setter
public class Corretora {

	// Dados da empresa
	@Size(max = 3000)
	@Column(name = "descricao")
	private String descricao;

}
