package com.cloud.impolator.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.impolator.api.v1.extract.CorretoraExtract;
import com.cloud.impolator.api.v1.extract.CustoOperacionalExtract;
import com.cloud.impolator.api.v1.extract.EspecificacaoDiversaExtract;
import com.cloud.impolator.api.v1.extract.ItemNotaExtract;
import com.cloud.impolator.api.v1.extract.LiquidoExtract;
import com.cloud.impolator.api.v1.extract.NotaExtract;
import com.cloud.impolator.api.v1.extract.ObservacaoExtract;
import com.cloud.impolator.api.v1.extract.ResumoFinanceiroExtract;
import com.cloud.impolator.api.v1.extract.ResumoNegocioExtract;
import com.cloud.impolator.domain.exception.ArquivoException;
import com.cloud.impolator.domain.exception.NotaNaoEncontradaException;
import com.cloud.impolator.domain.model.Corretora;
import com.cloud.impolator.domain.model.CustoOperacional;
import com.cloud.impolator.domain.model.EspecificacaoDiversa;
import com.cloud.impolator.domain.model.ItemNota;
import com.cloud.impolator.domain.model.Liquido;
import com.cloud.impolator.domain.model.Nota;
import com.cloud.impolator.domain.model.Observacao;
import com.cloud.impolator.domain.model.ResumoFinanceiro;
import com.cloud.impolator.domain.model.ResumoNegocio;
import com.cloud.impolator.domain.repository.NotaRepository;

/**
 * @author Maicon Fang
 */

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
	public Nota salvarPDF(MultipartFile file) {
		
		validaArquivo(file);

		Nota nota = NotaExtract.getValuesOfNotaNegociacao(file);
		Corretora corretora = CorretoraExtract.getValuesOfCorretora(file);	
		List<ItemNota> itemNota = ItemNotaExtract.getValueOfNegociosRealizados(file);
		ResumoNegocio resumoNegocio = ResumoNegocioExtract.getValuesOfResumoNegocio(file);
		EspecificacaoDiversa especificacaoDiverssa = EspecificacaoDiversaExtract.getValuesOfEspecificaoDiversa(file);
		Observacao observacao = ObservacaoExtract.getValuesOfObservacao(file);
		ResumoFinanceiro resumoFinanceiro = ResumoFinanceiroExtract.getValuesOfResumoFinanceiro(file);
		CustoOperacional custoOperacional = CustoOperacionalExtract.getValuesOfCustoOperacional(file);
		Liquido liquido = LiquidoExtract.getValuesOfLiquido(file);
		
		
		nota.setCorretora(corretora);
		nota.setItens(itemNota);
		nota.setResumoNegocio(resumoNegocio);
		nota.setEspecificacaoDiversa(especificacaoDiverssa);
		nota.setObservacao(observacao);
		nota.setResumoFinanceiro(resumoFinanceiro);
		nota.setCustoOperacional(custoOperacional);
		nota.setLiquido(liquido);
		
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
