package com.cloud.impolator.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.impolator.domain.exception.NotaNaoEncontradaException;
import com.cloud.impolator.domain.model.Nota;
import com.cloud.impolator.domain.repository.NotaRepository;

@Service
public class EmissaoNotaService {

	@Autowired
	private NotaRepository notaRepository;
	
	@Transactional
	public Nota emitir(Nota nota) {

		validarItens(nota);
		
		return notaRepository.save(nota);
	}
	
	public Nota buscarOuFalhar(Long idNota) {
		return notaRepository.findById(idNota)
			.orElseThrow(() -> new NotaNaoEncontradaException(idNota));
	}
	
	private void validarItens(Nota nota) {
		nota.getItens().forEach(item -> {
			item.setNota(nota);

		});
	}
}
