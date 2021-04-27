package com.cloud.impolator.api.v1.business;

import java.util.List;

import com.cloud.impolator.api.v1.utils.ImportadorUtils;
import com.cloud.impolator.domain.model.Nota;

public class XP implements ICorretora {

	public List<Nota> extract(String content) {
		return ImportadorUtils.extractDataFromNota(content);
	}

}
