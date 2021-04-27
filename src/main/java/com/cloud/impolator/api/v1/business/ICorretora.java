package com.cloud.impolator.api.v1.business;

import java.util.List;

import com.cloud.impolator.domain.model.Nota;

public interface ICorretora {
	
	List<Nota> extract(final String content);

}
