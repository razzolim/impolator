package com.cloud.impolator.api.core.repository.filter;

import java.util.ArrayList;
import java.util.List;

public class FiltroPesquisa {

	private List<ArgumentoPesquisa> argumentos = new ArrayList<>();
	private List<OrdemPesquisa> ordens = new ArrayList<>();
	private List<FiltroPesquisa> agrupamentosOR = new ArrayList<>();

	public FiltroPesquisa like(String nomeColuna, String valor) {
		if (valor != null && !valor.isEmpty()) {
			argumentos.add(new ArgumentoPesquisa(nomeColuna, valor, CondicaoPesquisa.LIKE));
		}
		return this;
	}
	
	public FiltroPesquisa equals(String nomeColuna, Object valor) {
		if (valor != null) {
			argumentos.add(new ArgumentoPesquisa(nomeColuna, valor, CondicaoPesquisa.EQUALS));
		}
		return this;
	}
	
	public FiltroPesquisa equals(String nomeColuna, String valor) {
		if (valor != null && !valor.trim().isEmpty()) {
			argumentos.add(new ArgumentoPesquisa(nomeColuna, valor, CondicaoPesquisa.EQUALS));
		}
		return this;
	}
	
	public FiltroPesquisa orderByAsc(String nomeColuna) {
		ordens.add(new OrdemPesquisa(nomeColuna, true));
		return this;
	}
	
	public FiltroPesquisa orderByDesc(String nomeColuna) {
		ordens.add(new OrdemPesquisa(nomeColuna, false));
		return this;
	}
	
	public FiltroPesquisa groupOR() {
		FiltroPesquisa filtroOR = new FiltroPesquisa();
		this.agrupamentosOR.add(filtroOR);
		return filtroOR;
	}

	public List<ArgumentoPesquisa> getArgumentos() {
		return argumentos;
	}
	
	public List<OrdemPesquisa> getOrdens() {
		return ordens;
	}
	
	public List<FiltroPesquisa> getAgrupamentosOR() {
		return agrupamentosOR;
	}

	public class ArgumentoPesquisa {

		private String nomeColuna;
		private Object valor;
		private CondicaoPesquisa condicao;

		public ArgumentoPesquisa(String nomeColuna, Object valor, CondicaoPesquisa condicao) {
			this.nomeColuna = nomeColuna;
			this.valor = valor;
			this.condicao = condicao;
		}

		public String getNomeColuna() {
			return nomeColuna;
		}

		public Object getValor() {
			return valor;
		}

		public CondicaoPesquisa getCondicao() {
			return condicao;
		}
	}

	public class OrdemPesquisa {

		private String nomeColuna;
		private boolean asc;
		
		public OrdemPesquisa(String nomeColuna, boolean asc) {
			this.nomeColuna = nomeColuna;
			this.asc = asc;
		}

		public String getNomeColuna() {
			return nomeColuna;
		}

		public boolean isAsc() {
			return asc;
		}
	}

	public enum CondicaoPesquisa {
		LIKE, EQUALS;
	}
}
