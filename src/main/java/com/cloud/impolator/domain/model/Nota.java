package com.cloud.impolator.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.AbstractAggregateRoot;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Maicon Fang
 */


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Nota extends AbstractAggregateRoot<Nota> {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@NotNull
	@Column(name = "numNota")
	private String numNota;

	@NotNull
	@Column(name = "folha")
	private String folha;
	
	@Column(name = "dataPregao")
	private String dataPregao;
	
	@OneToMany(mappedBy = "nota", cascade = CascadeType.ALL)
	private List<ItemNota> itens = new ArrayList<>();
	
	@Embedded
	private Corretora corretora;
	
	@Embedded
	private ResumoNegocio resumoNegocio;

	@Embedded
	private EspecificacaoDiversa especificacaoDiversa;
	
	@Embedded
	private Observacao observacao;
	
	@Embedded
	private ResumoFinanceiro resumoFinanceiro;
	
	@Embedded
	private CustoOperacional custoOperacional;

	@Embedded
	private Liquido liquido;

}
