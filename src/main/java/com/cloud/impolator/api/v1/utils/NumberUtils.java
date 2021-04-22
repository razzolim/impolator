package com.cloud.impolator.api.v1.utils;

import java.math.BigDecimal;

public class NumberUtils {
	
	private NumberUtils() {
		// NA
	}
	
	public static BigDecimal parseToBigDecimal(String value) {
		if (value.contains(",")) {
			value = value.replace(",", ".");
		}
		return new BigDecimal(value);
	}

}
