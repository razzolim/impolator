package com.cloud.impolator.api.v1.extract;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;
import com.cloud.impolator.domain.model.Corretora;


/**
 * @author Maicon Fang
 */

public class CorretoraExtract extends UtilsExtract {


	public static HashMap<String, Integer> getPositionOfCorretora(MultipartFile fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			if (map.get("startPosition") == null && getTextFromCoordinate(fileName, 32, y, 800, 1).contains("Folha")) {
				map.put("startPosition", y +12);
			}

			if (map.get("startPosition") != null && getTextFromCoordinate(fileName, 32, y, 80, 1).equals("Cliente")) {
				map.put("endPosition", y-1);
				break;
			}
		}

		return map;
	}

	public static Corretora getValuesOfCorretora(MultipartFile fileName){

		HashMap<String, Integer> map = getPositionOfCorretora(fileName);
		Integer startPosition = map.get("startPosition");

		Corretora cor = new Corretora();

		cor.setDescricao(getTextFromCoordinate(fileName, 32, startPosition, 700, 70).trim()); // Value of column "Dados da corretora"

		return cor;

	}
}
