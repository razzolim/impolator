package com.cloud.impolator.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.impolator.entity.NotaNegociacao;
import com.cloud.impolator.repository.NotaNegociacaoRepository;

@Service
public class NotaNegociacaoService {
	
	@Autowired
	private NotaNegociacaoRepository notaNegociacaoRepository;
	
	@Transactional
	public NotaNegociacao salvar(NotaNegociacao notaNegociacao) {
		return notaNegociacaoRepository.save(notaNegociacao);
	}

}
