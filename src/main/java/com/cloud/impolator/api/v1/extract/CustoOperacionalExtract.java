package com.cloud.impolator.api.v1.extract;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.cloud.impolator.domain.model.CustoOperacional;

/**
 * @author Maicon Fang
 */

public class CustoOperacionalExtract extends UtilsExtract {

	public static HashMap<String, Integer> getPositionOfCustoOperacional(MultipartFile fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			if (map.get("startPosition") == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Custos Operacionais")) {
				map.put("startPosition", y);
			}

			if (map.get("startPosition") != null && getTextFromCoordinate(fileName, 300, y, 100, 1).contains("Total Custos / Despesas")) {
				map.put("endPosition", y);
				break;
			}
		}
		return map;
	}

	public static CustoOperacional getValuesOfCustoOperacional(MultipartFile fileName){

		HashMap<String, Integer> map = getPositionOfCustoOperacional(fileName);
		Integer startPosition = map.get("startPosition");
		Integer endPosition = map.get("endPosition");

		CustoOperacional cusOpe = new CustoOperacional();
		for (int y = startPosition; y <= endPosition; y++) {

			if (cusOpe.getTaxaOperacional() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Taxa Operacional")) {
				cusOpe.setTaxaOperacional(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "Taxa Operacional"
			}

			if (cusOpe.getExecucao() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Execução")) {
				cusOpe.setExecucao(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "Execução"
			}

			if (cusOpe.getTaxaCustodia() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Taxa de Custódia")) {
				cusOpe.setTaxaCustodia(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "Taxa de Custódia"
			}

			if (cusOpe.getImpostos() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Impostos")) {
				cusOpe.setImpostos(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "Impostos"
			}

			if (cusOpe.getIrrf() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).contains("I.R.R.F")) {
				cusOpe.setIrrf(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "I.R.R.F"
			}

			if (cusOpe.getOutros() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Outros")) {
				cusOpe.setOutros(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "Outros"
			}

			if (cusOpe.getTotalCustosDespesas() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Total Custos / Despesas")) {
				cusOpe.setTotalCustosDespesas(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "Total Custos / Despesas"
			}

		}

		return cusOpe;
	}


}
