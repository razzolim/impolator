package com.cloud.impolator.api.v1.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.impolator.api.v1.assembler.NotaInputDisassembler;
import com.cloud.impolator.api.v1.assembler.NotaModelAssembler;
import com.cloud.impolator.api.v1.model.NotaModel;
import com.cloud.impolator.api.v1.model.input.NotaInput;
import com.cloud.impolator.api.v1.openapi.controller.NotaControllerOpenApi;
import com.cloud.impolator.domain.exception.EntidadeNaoEncontradaException;
import com.cloud.impolator.domain.exception.NegocioException;
import com.cloud.impolator.domain.model.Nota;
import com.cloud.impolator.domain.service.EmissaoNotaService;



// Isso é a mesma coia que resource / endpoints
@RestController
@RequestMapping(path ="/v1/notas", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotaController implements NotaControllerOpenApi {

	@Autowired
	private EmissaoNotaService emissaoNota;
	
	@Autowired
	private NotaInputDisassembler notaInputDisassembler;
	
	@Autowired
	private NotaModelAssembler notaModelAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public NotaModel adicionar(@Valid @RequestBody NotaInput pedidoInput) {
		try {
			Nota novoPedido = notaInputDisassembler.toDomainObject(pedidoInput);

			novoPedido = emissaoNota.emitir(novoPedido);

			return notaModelAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

}
