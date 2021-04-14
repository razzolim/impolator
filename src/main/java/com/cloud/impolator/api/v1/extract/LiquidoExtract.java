package com.cloud.impolator.api.v1.extract;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.cloud.impolator.domain.model.Liquido;

/**
 * @author Maicon Fang
 */

public class LiquidoExtract extends UtilsExtract {

	public static HashMap<String, Integer> getPositionOfLiquido(MultipartFile fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			if (map.get("startPosition") == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Total Custos / Despesas")) {
				map.put("startPosition", y);
			}

			if (map.get("startPosition") != null && getTextFromCoordinate(fileName, 300, y, 100, 1).contains("Líquido para")) {
				map.put("endPosition", y);
				break;
			}
		}
		return map;
	}

	public static Liquido getValuesOfLiquido(MultipartFile fileName){

		HashMap<String, Integer> map = getPositionOfLiquido(fileName);
		Integer startPosition = map.get("startPosition");
		Integer endPosition = map.get("endPosition");

		Liquido liq = new Liquido();
		for (int y = startPosition; y <= endPosition; y++) {

			if (liq.getData() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).contains("Líquido para")) {
				liq.setData(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of line "Data"
			}
			
			if (liq.getValor() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).contains("Líquido para")) {
				liq.setValor(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of line "Data"
			}
			
			if (liq.getObservacao() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).contains("Líquido para")) {
				liq.setObservacao(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of line "Data"
			}

		}

		return liq;
	}


}
