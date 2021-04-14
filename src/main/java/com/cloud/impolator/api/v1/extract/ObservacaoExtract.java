package com.cloud.impolator.api.v1.extract;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.cloud.impolator.domain.model.Observacao;

/**
 * @author Maicon Fang
 */

public class ObservacaoExtract extends UtilsExtract {
	
	public static HashMap<String, Integer> getPositionOfObservacoes(MultipartFile fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			if (map.get("startPosition") == null && getTextFromCoordinate(fileName, 32, y, 80, 1).equals("(*) Observações")) {
				map.put("startPosition", y);
			}

			if (map.get("startPosition") != null && getTextFromCoordinate(fileName, 32, y, 80, 1).contains("Capitais e regiões")) {
				map.put("endPosition", y-1);
				break;
			}
		}
		return map;
	}

	public static Observacao getValuesOfObservacao(MultipartFile fileName){

		HashMap<String, Integer> map = getPositionOfObservacoes(fileName);
		Integer startPosition = map.get("startPosition");
		Integer endPosition = map.get("endPosition");

		Observacao obs = new Observacao();
		String description = "";
		for (int y = startPosition; y < endPosition; y++) {

			// Fist column
			if (!getTextFromCoordinate(fileName, 32, y, 116, 1).equals("(*) Observações") && getTextFromCoordinate(fileName, 32, y, 116, 1).length() > 1   ) {
				description = description + getTextFromCoordinate(fileName, 32, y, 116, 1) + "; ";
			}

			// Second column
			if (!getTextFromCoordinate(fileName, 148, y, 80, 1).equals("(*) Observações") && getTextFromCoordinate(fileName, 148, y, 80, 1).length() > 1   ) {
				description = description + getTextFromCoordinate(fileName, 148, y, 80, 1) + "; ";
			}

			// Third column
			if (!getTextFromCoordinate(fileName, 230, y, 80, 1).equals("(*) Observações") && getTextFromCoordinate(fileName, 230, y, 70, 1).length() > 1   ) {
				description = description + getTextFromCoordinate(fileName, 230, y, 80, 1) + "; ";
			}
		}
		obs.setDescricao(description);

		return obs;
	}

	
}
