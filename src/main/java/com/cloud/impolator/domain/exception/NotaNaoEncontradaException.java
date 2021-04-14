package com.cloud.impolator.domain.exception;

/**
 * @author Maicon Fang
*/

public class NotaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public NotaNaoEncontradaException(Long idNota) {
		super(String.format("Não existe uma nota com id %s", idNota));
	}
	
}