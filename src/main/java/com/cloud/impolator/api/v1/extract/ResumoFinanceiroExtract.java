package com.cloud.impolator.api.v1.extract;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.cloud.impolator.domain.model.ResumoFinanceiro;

/**
 * @author Maicon Fang
 */

public class ResumoFinanceiroExtract extends UtilsExtract {
	
	public static HashMap<String, Integer> getPositionOfResumoFinanceiro(MultipartFile fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			if (map.get("startPosition") == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Resumo Financeiro")) {
				map.put("startPosition", y);
			}

			if (map.get("startPosition") != null && getTextFromCoordinate(fileName, 300, y, 100, 1).contains("Custos Operacionais")) {
				map.put("endPosition", y-1);
				break;
			}
		}
		return map;
	}

	public static ResumoFinanceiro getValuesOfResumoFinanceiro(MultipartFile fileName){

		HashMap<String, Integer> map = getPositionOfResumoFinanceiro(fileName);
		Integer startPosition = map.get("startPosition");
		Integer endPosition = map.get("endPosition");

		ResumoFinanceiro resFin = new ResumoFinanceiro();
		for (int y = startPosition; y < endPosition; y++) {

			if (resFin.getValorLiquidoOperacoes() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Valor líquido das operações")   ) {
				resFin.setValorLiquidoOperacoes(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "Valor líquido das operações"
			}

			if (resFin.getTaxaLiquidacao() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Taxa de liquidação")   ) {
				resFin.setTaxaLiquidacao(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "Taxa deliquidação"
			}

			if (resFin.getTaxaRegistro() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Taxa de Registro")   ) {
				resFin.setTaxaRegistro(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "Taxa de Registro"
			}

			if (resFin.getTotalcblc() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Total CBLC")   ) {
				resFin.setTotalcblc(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "Total CBLC"
			}

			if (resFin.getTaxaTermoOpcoes() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Taxa de termo/opções")   ) {
				resFin.setTaxaTermoOpcoes(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "Taxa determo/opções"
			}

			if (resFin.getTaxaAna() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Taxa A.N.A.")   ) {
				resFin.setTaxaAna(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "Taxa A.N.A."
			}

			if (resFin.getEmolumentos() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Emolumentos")   ) {
				resFin.setEmolumentos(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "Emolumentos"
			}

			if (resFin.getTotalBovespaSoma() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Total Bovespa / Soma")   ) {
				resFin.setTotalBovespaSoma(new BigDecimal(getTextFromCoordinate(fileName, 508, y, 40, 1))); // Value of column "Total Bovespa / Soma"
			}
		}

		return resFin;
	}

	
}
