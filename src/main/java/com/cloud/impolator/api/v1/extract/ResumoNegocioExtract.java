package com.cloud.impolator.api.v1.extract;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.cloud.impolator.domain.model.ResumoNegocio;

public class ResumoNegocioExtract extends UtilsExtract {

	
	public static HashMap<String, Integer> getPositionOfResumoDosNegocios(MultipartFile fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			if (map.get("startPosition") == null && getTextFromCoordinate(fileName, 32, y, 80, 1).equals("Resumo dos Negócios")) {
				map.put("startPosition", y +1);
			}

			if (map.get("startPosition") != null && getTextFromCoordinate(fileName, 32, y, 80, 1).equals("Especificações diversas")) {
				map.put("endPosition", y-1);
				break;
			}

		}

		return map;
	}

	public static ResumoNegocio getValuesOfResumoNegocio(MultipartFile fileName){

		HashMap<String, Integer> map = getPositionOfResumoDosNegocios(fileName);
		Integer startPosition = map.get("startPosition");
		Integer endPosition = map.get("endPosition");

		ResumoNegocio resNeg = new ResumoNegocio();
		for (int y = startPosition; y < endPosition; y++) {

			if (getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Resumo dos Negócios")) {
				System.out.println("Achou o resumo dos negócios");
			}

			if (resNeg.getDebentures() == null && getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Debêntures")) {
				resNeg.setDebentures(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of fied "Debêntures"
			}

			if (resNeg.getVendasVista() == null &&getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Vendas à vista")) {
				resNeg.setVendasVista(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of fied "Vendas à vista"
			}

			if (resNeg.getComprasVista() == null && getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Compras à vista")) {
				resNeg.setComprasVista(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of field "Compras à vista"
			}

			if (resNeg.getOpcoesCompras() == null && getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Opções - compras")) {
				resNeg.setOpcoesCompras(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of field "Opções - compras"
			}

			if (resNeg.getOpcoesVendas() == null && getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Opções - vendas")) {
				resNeg.setOpcoesVendas(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of field "Opções - vendas"
			}

			if (resNeg.getOperacoesTermo() == null && getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Operações à termo")) {
				resNeg.setOperacoesTermo(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of field "Operações à termo"
			}

			if (resNeg.getValorOperCTitulosPubl() == null && getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Valor das oper. c/ títulos públ. (v. nom.)")) {
				resNeg.setValorOperCTitulosPubl(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of field "Valor das oper.c/ títulos públ. (v. nom.)"
			}

			if (resNeg.getValorOperacoes() == null && getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Valor das operações")) {
				resNeg.setValorOperacoes(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of field "Valor das operações"
				break;
			}

		}

		return resNeg;

	}
	
}
