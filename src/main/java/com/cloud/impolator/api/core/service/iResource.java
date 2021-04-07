package com.cloud.impolator.api.core.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

/**
 * 
 * @author tomas.guerreiro
 *
 */
public interface iResource<T, ID> {

	public ResponseEntity<?> listar();
	
	public ResponseEntity<?> salvar(T entidade, HttpServletResponse response);
	
	public ResponseEntity<?> apagar(ID id);
	
	public ResponseEntity<?> atualizar(ID id, T entidade);
	
	public ResponseEntity<?> buscarPorID(ID id);
	
	//public Page<T> listarPaginado(ID start, ID end, ID total);
	
}
