package com.cloud.impolator.api.core.repository;

import java.util.Date;

public interface HistoricoInclusao {

	public void setInclusao(Date inclusao);

	public void setAtualizacao(Date atualizacao);
	
	public boolean isNew();
}
