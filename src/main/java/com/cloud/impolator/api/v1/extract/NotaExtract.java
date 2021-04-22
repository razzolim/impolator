package com.cloud.impolator.api.v1.extract;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.cloud.impolator.domain.model.Nota;

/**
 * @author Maicon Fang
 */

public class NotaExtract extends UtilsExtract {


	public static HashMap<String, Integer> getPositionOfNotaNegociacao(MultipartFile fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			if (getTextFromCoordinate(fileName, 32, y, 800, 1).contains("Nr. nota")) {
				map.put("startPosition", y);
				map.put("endPosition", y+30);
				break;
			}
		}

		return map;
	}

	public static Nota getValuesOfNotaNegociacao(MultipartFile fileName){

		HashMap<String, Integer> map = getPositionOfNotaNegociacao(fileName);
		Integer startPosition = map.get("startPosition");
		Integer endPosition = map.get("endPosition");

		Nota nota = new Nota();
		for (int y = startPosition; y < endPosition; y++) {

			if (nota.getNumNota() == null && getTextFromCoordinate(fileName, 430, y, 35, 1).equals("Nr. nota")) {
				nota.setNumNota(Integer.parseInt(getTextFromCoordinate(fileName, 430, y+1, 35, 8))); // Value of column "Nr. nota"
			}

			if (nota.getFolha() == null && getTextFromCoordinate(fileName, 475, y, 40, 1).equals("Folha")) {
				nota.setFolha(Integer.parseInt(getTextFromCoordinate(fileName, 475, y+1, 40, 8))); // Value of column "Folha"
			}

			// Value of column "Data pregão"
			if (nota.getDataPregao() == null && getTextFromCoordinate(fileName, 516, y, 40, 1).equals("Data pregão")) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
				LocalDate date = LocalDate.parse(getTextFromCoordinate(fileName, 516, y, 40, 1), formatter);

				nota.setDataPregao(date); 
				break;
			}

		}

		return nota;

	}

}
