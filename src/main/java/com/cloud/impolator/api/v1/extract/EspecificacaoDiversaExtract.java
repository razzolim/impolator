package com.cloud.impolator.api.v1.extract;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.cloud.impolator.domain.model.EspecificacaoDiversa;

public class EspecificacaoDiversaExtract extends UtilsExtract {

	
	public static HashMap<String, Integer> getPositionOfEspecificaoDiversa(MultipartFile fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			if (map.get("startPosition") == null && getTextFromCoordinate(fileName, 32, y, 80, 1).equals("Especificações diversas")) {
				map.put("startPosition", y +1);
			}

			if (map.get("startPosition") != null && getTextFromCoordinate(fileName, 32, y, 80, 1).equals("(*) Observações")) {
				map.put("endPosition", y-1);
				break;
			}
		}

		return map;
	}

	public static EspecificacaoDiversa getValuesOfEspecificaoDiversa(MultipartFile fileName){

		HashMap<String, Integer> map = getPositionOfEspecificaoDiversa(fileName);
		Integer startPosition = map.get("startPosition");
		Integer endPosition = map.get("endPosition");

		EspecificacaoDiversa espDiv = new EspecificacaoDiversa();
		String description = "";
		for (int y = startPosition; y < endPosition; y++) {

			if (getTextFromCoordinate(fileName, 32, y, 268, 1).length() > 1 ) {
				description = description + getTextFromCoordinate(fileName, 32, y, 268, 1) + "; "; // Value of fied "Especificações diversas"
			}
		}

		espDiv.setDescricao(description);

		return espDiv;

	}

	
}
