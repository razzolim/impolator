package com.cloud.impolator.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.impolator.api.v1.business.Business;
import com.cloud.impolator.domain.exception.ArquivoException;
import com.cloud.impolator.domain.exception.NotaNaoEncontradaException;
import com.cloud.impolator.domain.model.Nota;
import com.cloud.impolator.domain.repository.NotaRepository;


/**
 * @author Maicon Fang
 */

@Service
public class NotaService {

	@Autowired
	private NotaRepository notaRepository;

	private static String password;

	private static int total = 0;

	@Transactional
	public Nota salvarNota(Nota nota) {

		validarItens(nota);

		return notaRepository.save(nota);
	}

	@Transactional
	public Nota salvarPDFRegex(MultipartFile file) throws IOException {

		validaArquivo(file);

		String path = "/notas/";
		String fileName = file.getOriginalFilename(); // Nome do arquivo

		InputStream is = NotaService.class.getResourceAsStream(path + fileName);
		byte[] bytes = is.readAllBytes();

		String arq = Base64.getEncoder().encodeToString(bytes);

		List<Nota> listNota = null;
		Nota notaSalva = null;
		
		try {
			listNota = execute(Arrays.asList(arq), password);
			total++;

			for (Nota nota : listNota) {
				validarItens(nota);

				if (nota != null  ) {
					notaRepository.save(nota);
					notaSalva = nota;
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return notaSalva;
	}


	private static List<Nota> execute(List<String> pFiles, final String password) throws Exception {

		Business b = new Business();
		List<Nota> teste = b.extract(pFiles, password);

		return teste;

		//		LocalDate inicio = LocalDate.of(2020, Month.APRIL, 1);
		//		Period p = Period.ofMonths(1);
		//		b.calcularDARF(inicio, inicio.plus(p).minusDays(1));
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
