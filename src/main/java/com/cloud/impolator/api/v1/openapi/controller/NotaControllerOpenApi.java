package com.cloud.impolator.api.v1.openapi.controller;

import com.cloud.impolator.api.v1.model.NotaModel;
import com.cloud.impolator.api.v1.model.input.NotaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Notas")
public interface NotaControllerOpenApi {

	
	@ApiOperation("Registra um pedido")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Pedido registrado"),
	})
	NotaModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true)
			NotaInput notaInput);
}
