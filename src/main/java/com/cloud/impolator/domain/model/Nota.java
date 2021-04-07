package com.cloud.impolator.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.domain.AbstractAggregateRoot;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
	
	// Dados da empresa
	@Size(max = 3000)
	@Column(name = "descricao")
	private String descricao;
	
	@OneToMany(mappedBy = "nota", cascade = CascadeType.ALL)
	private List<ItemNota> itens = new ArrayList<>();

}
