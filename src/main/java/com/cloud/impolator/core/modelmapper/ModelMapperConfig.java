package com.cloud.impolator.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloud.impolator.api.v1.model.input.ItemNotaInput;
import com.cloud.impolator.domain.model.ItemNota;

/**
 * @author Maicon Fang
*/

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		
		modelMapper.createTypeMap(ItemNotaInput.class, ItemNota.class)
			.addMappings(mapper -> mapper.skip(ItemNota::setId));
		
		return modelMapper;
	}
	
}

