package com.cloud.impolator.api.core.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cloud.impolator.api.core.repository.filter.FiltroPesquisa;


public interface CrudService<T, ID> {

	public List<T> listar();
	
	public T salvar(T entidade);
	
	public void apagar(ID id);
	
	public T atualizar(ID id, T entidade);
	
	public T buscarPorID(ID id);
	
	public Page<T> listarPaginado(FiltroPesquisa filtroPesquisa, Pageable pageable);
	
}
