package com.cloud.impolator.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cloud.impolator.api.v1.model.input.NotaInput;
import com.cloud.impolator.domain.model.Nota;

@Component
public class NotaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Nota toDomainObject(NotaInput notaInput) {
		return modelMapper.map(notaInput, Nota.class);
	}
	
	public void copyToDomainObject(NotaInput notaInput, Nota nota) {
		modelMapper.map(notaInput, nota);
	}
	
}
