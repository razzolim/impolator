package com.cloud.impolator.api.v1.extract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cloud.impolator.domain.model.ItemNota;

public class ItemNotaExtract extends UtilsExtract {


	public static HashMap<String, Integer> getTamanhoListaItens(MultipartFile fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			try {
				if (map.get("startPosition") == null && getTextFromCoordinate(fileName, 32, y, 600, 1).contains("Q Negociação C/V Tipo mercado Prazo Especificação do")) {
					map.put("startPosition", y +1);
				}

				if (map.get("startPosition") != null && getTextFromCoordinate(fileName, 32, y, 80, 1).equals("Resumo dos Negócios")) {
					map.put("endPosition", y-1);
					break;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


		return map;
	}

	public static List<ItemNota> getValueOfNegociosRealizados(MultipartFile fileName) {

		ArrayList<ItemNota> listNegRea = new ArrayList<ItemNota>();

		Integer columnQ = 30;
		Integer columnNeg = 42;
		Integer columnCV = 90;
		Integer columnTipMer = 94;
		Integer columnPra = 158;
		Integer columnEspTit = 178;
		Integer columnObs = 326;
		Integer columnQua = 340;
		Integer columnPreAju = 394;
		Integer columnValOpeAju = 448;
		Integer columnDC = 540;

		HashMap<String, Integer> map = getTamanhoListaItens(fileName);
		Integer startListOfItens = map.get("startPosition");
		Integer endListOfItens = map.get("endPosition");

		for (int y = startListOfItens; y < endListOfItens; y++) {

			try {
				if (getTextFromCoordinate(fileName, columnQ, y, 100, 1).length() > 1 ) {

					ItemNota objectNegRea = new ItemNota();

					objectNegRea.setQ(getTextFromCoordinate(fileName, columnQ, y, 10, 1));// Value of column "Negócios realizados - Q"

					objectNegRea.setNegociacao(getTextFromCoordinate(fileName, columnNeg, y, 45, 1)); // Value of column "Negócios realizados - Negociação"

					objectNegRea.setCv(getTextFromCoordinate(fileName, columnCV, y, 10, 1)); // Value of column "Negócios realizados - C/V"

					objectNegRea.setTipoMercadoria(getTextFromCoordinate(fileName, columnTipMer, y, 55, 1)); // Value of column "Negócios realizados - Tipo mercado"

					objectNegRea.setPrazo(getTextFromCoordinate(fileName, columnPra, y, 20, 1)); // Value of column "Negócios realizados - Prazo"

					objectNegRea.setEspecificacaoTitulo(getTextFromCoordinate(fileName, columnEspTit, y, 150, 1)); // Value of column "Especificação do título"

					objectNegRea.setObs(getTextFromCoordinate(fileName, columnObs, y, 20, 1)); // Value of column "Obs"

					objectNegRea.setQuantidade(getTextFromCoordinate(fileName, columnQua, y, 54, 1)); // Value of column "Quantidade"

					objectNegRea.setPrecoAjuste(getTextFromCoordinate(fileName, columnPreAju, y, 54, 1)); // Value of column "Preço Ajuste"

					objectNegRea.setValorOperacao(getTextFromCoordinate(fileName, columnValOpeAju, y, 92, 1)); // Value of column "Valor Operação / Ajuste"

					objectNegRea.setDc(getTextFromCoordinate(fileName, columnDC, y, 20, 1)); // Value of column "D/C"

					listNegRea.add(objectNegRea);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return listNegRea;

	}
}
