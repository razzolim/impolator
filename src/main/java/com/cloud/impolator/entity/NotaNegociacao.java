package com.cloud.impolator.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class NotaNegociacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "numNota")
	private Integer numNota;

	@NotNull
	@Column(name = "folha")
	private Integer folha;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataPregao")
	private Date dataPregao;
	

}
