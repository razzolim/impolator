package com.cloud.impolator.api.v1.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.impolator.api.v1.assembler.NotaModelAssembler;
import com.cloud.impolator.api.v1.model.NotaModel;
import com.cloud.impolator.domain.exception.ArquivoException;
import com.cloud.impolator.domain.model.Nota;
import com.cloud.impolator.domain.service.NotaService;

/**
 * @author Maicon Fang
 */

@RestController
@RequestMapping(path ="/v1/notas", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotaController {

	@Autowired
	private NotaService notaService;
	
	@Autowired
	private NotaModelAssembler notaModelAssembler;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/enviarpdfregex")
	public NotaModel salvar(@RequestParam("file") MultipartFile file) throws IOException {
		try {
			
			Nota notaSalva = notaService.salvarPDFRegex(file);

			return notaModelAssembler.toModel(notaSalva);
			
		} catch (ArquivoException e) {
			throw new ArquivoException(String.format(e.getMessage()));
		}
	}


}
