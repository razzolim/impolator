package com.cloud.impolator.api.v1.business;

import com.cloud.impolator.api.v1.constant.ClearConstants;
import com.cloud.impolator.api.v1.constant.XPConstants;

public final class CorretoraFactory {

	private CorretoraFactory() {
		// NA
	}

	/**
	 * 
	 * @param corretora
	 * @return
	 */
	public static final ICorretora factory(final String corretora) {
		switch (corretora) {
		case ClearConstants.NOME_CORRETORA:
			return new Clear();
		case XPConstants.NOME_CORRETORA:
			return new XP();
		default:
			return null;
		}
	}

}
