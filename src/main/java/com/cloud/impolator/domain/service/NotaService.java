package com.cloud.impolator.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.impolator.api.v1.extract.CorretoraExtract;
import com.cloud.impolator.api.v1.extract.EspecificacaoDiversaExtract;
import com.cloud.impolator.api.v1.extract.ItemNotaExtract;
import com.cloud.impolator.api.v1.extract.NotaExtract;
import com.cloud.impolator.api.v1.extract.ResumoNegocioExtract;
import com.cloud.impolator.domain.exception.ArquivoException;
import com.cloud.impolator.domain.exception.NotaNaoEncontradaException;
import com.cloud.impolator.domain.model.Corretora;
import com.cloud.impolator.domain.model.EspecificacaoDiversa;
import com.cloud.impolator.domain.model.ItemNota;
import com.cloud.impolator.domain.model.Nota;
import com.cloud.impolator.domain.model.ResumoNegocio;
import com.cloud.impolator.domain.repository.NotaRepository;

@Service
public class NotaService {

	@Autowired
	private NotaRepository notaRepository;
	
	@Transactional
	public Nota salvarNota(Nota nota) {

		validarItens(nota);
		
		return notaRepository.save(nota);
	}
	
	
	@Transactional
	public Nota salvarNotaComPDF(MultipartFile file) {
		
		validaArquivo(file);

		Nota nota = NotaExtract.getValuesOfNotaNegociacao(file);
		Corretora corretora = CorretoraExtract.getValuesOfCorretora(file);	
		List<ItemNota> itemNota = ItemNotaExtract.getValueOfNegociosRealizados(file);
		ResumoNegocio resumoNegocio = ResumoNegocioExtract.getValuesOfResumoNegocio(file);
		EspecificacaoDiversa especificacaoDiverssa = EspecificacaoDiversaExtract.getValuesOfEspecificaoDiversa(file);
		
		
		nota.setCorretora(corretora);
		nota.setItens(itemNota);
		nota.setResumoNegocio(resumoNegocio);
		nota.setEspecificacaoDiversa(especificacaoDiverssa);
		
		validarItens(nota);
		
		return notaRepository.save(nota);
	}
	
	public void validaArquivo(MultipartFile file) {
		if (!file.getContentType().equals("application/pdf")) {
			throw new ArquivoException(String.format("Formato do arquivo não aceito. '%s' ", 
					file.getOriginalFilename()));
		}
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
