package com.cloud.impolator.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cloud.impolator.api.v1.controller.NotaController;
import com.cloud.impolator.api.v1.model.NotaModel;
import com.cloud.impolator.domain.model.Nota;

@Component
public class NotaModelAssembler 
		extends RepresentationModelAssemblerSupport<Nota, NotaModel> {

	@Autowired
	private ModelMapper modelMapper;

	public NotaModelAssembler() {
		super(NotaController.class, NotaModel.class);
	}

	@Override
	public NotaModel toModel(Nota nota) {
		NotaModel notaModel = createModelWithId(nota.getId(), nota);
		modelMapper.map(nota, notaModel);
		
		return notaModel;
	}

}
