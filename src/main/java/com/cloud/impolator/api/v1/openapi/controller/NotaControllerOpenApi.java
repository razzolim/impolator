package com.cloud.impolator.api.v1.openapi.controller;

import com.cloud.impolator.api.v1.model.NotaModel;
import com.cloud.impolator.api.v1.model.input.NotaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Maicon Fang
*/

@Api(tags = "Notas")
public interface NotaControllerOpenApi {

	
	@ApiOperation("Registra uma nota")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Nota registrada"),
	})
	NotaModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova nota", required = true)
			NotaInput notaInput);
}
