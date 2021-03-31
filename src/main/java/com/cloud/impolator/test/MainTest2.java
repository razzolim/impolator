package com.cloud.impolator.test;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class MainTest2 {


	public static void main( String[] args ) throws IOException {

		//		String fileName = "C:\\Users\\mfang\\Downloads\\notas.pdf";
		//String fileName = "C:\\Users\\mfang\\Downloads\\manyitens.pdf";
		//String fileName = "C:\\Users\\mfang\\Downloads\\notas - Editado.pdf";

		String fileName = "C:\\Desenvolvimento\\Projetos\\Impolator\\Documentos, anotações, geral\\Notas\\notas.pdf";

		// getItensNegociosRealizados(fileName);
		// getValuesOfResumoDosNegocios(fileName);
		// getValuesOfEspecificacoesDiversas(fileName);
		// getValuesOfObservacoes(fileName);
		// getValuesOfResumoFinanceiro(fileName);
		// getValuesOfCustosOperacionais(fileName);
		getValuesOfLiquido(fileName);

		//	HashMap<String, Integer> teste = getTamanhoListaItens(fileName);
		//	teste.get("startListOfItens");
		//	teste.get("endListOfItens");
		//	System.out.println(teste.get("startListOfItens"));
		//	System.out.println(teste.get("endListOfItens"));

	}

	public static String getTextFromCoordinate(String filepath,int x,int y,int width,int height) {
		String result = "";
		try (PDDocument document = PDDocument.load(new File(filepath))) {

			if (!document.isEncrypted()) {

				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);
				// Rectangle rect = new Rectangle(260, 35, 70, 10);
				Rectangle rect = new Rectangle(x,y,width,height);
				stripper.addRegion("class1", rect);
				PDPage firstPage = document.getPage(0);
				stripper.extractRegions( firstPage );
				// System.out.println("Text in the area:" + rect);
				result = stripper.getTextForRegion("class1");

			}
		} catch (IOException e){
			System.err.println("Exception while trying to read pdf document - " + e);
		}
		return result.trim();
	}

	public static HashMap<String, Integer> getTamanhoListaItens(String fileName) {


		HashMap<String, Integer> map = new HashMap<>();


		for (int y = 0; y < 1000; y++) {

			if (getTextFromCoordinate(fileName, 32, y, 600, 1).contains("Q Negociação C/V Tipo mercado Prazo Especificação do")) {
				map.put("startListOfItens", y +1);
			}

			if (getTextFromCoordinate(fileName, 32, y, 80, 1).equals("Resumo dos Negócios")) {
				map.put("endListOfItens", y-1);
				break;
			}

		}


		return map;
	}

	public static List<NegociosRealizados> getItensNegociosRealizados(String fileName) {

		ArrayList<NegociosRealizados> listNegRea = new ArrayList<NegociosRealizados>();

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
		Integer startListOfItens = map.get("startListOfItens");
		Integer endListOfItens = map.get("endListOfItens");

		for (int y = startListOfItens; y < endListOfItens; y++) {

			if (getTextFromCoordinate(fileName, columnQ, y, 100, 1).length() > 1 ) {

				NegociosRealizados objectNegRea = new NegociosRealizados();

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

		}

		return listNegRea;

	}

	public static HashMap<String, Integer> getPositionOfResumoDosNegocios(String fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			if (getTextFromCoordinate(fileName, 32, y, 80, 1).equals("Resumo dos Negócios")) {
				map.put("startPosition", y +1);
			}

			if (getTextFromCoordinate(fileName, 32, y, 80, 1).equals("Especificações diversas")) {
				map.put("endPosition", y-1);
				break;
			}

		}

		return map;
	}


	public static ResumoNegocios getValuesOfResumoDosNegocios(String fileName){

		HashMap<String, Integer> map = getPositionOfResumoDosNegocios(fileName);
		Integer startPosition = map.get("startPosition");
		Integer endPosition = map.get("endPosition");

		ResumoNegocios resNeg = new ResumoNegocios();
		for (int y = startPosition; y < endPosition; y++) {

			if (getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Resumo dos Negócios")) {
				System.out.println("Achou o resumo dos negócios");
			}

			if (getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Debêntures")) {
				resNeg.setDebentures(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of fied "Debêntures"
			}

			if (getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Vendas à vista")) {
				resNeg.setVendasVista(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of fied "Vendas à vista"
			}

			if (getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Compras à vista")) {
				resNeg.setComprasVista(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of field "Compras à vista"
			}

			if (getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Opções - compras")) {
				resNeg.setOpcoesCompras(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of field "Opções - compras"
			}

			if (getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Opções - vendas")) {
				resNeg.setOpcoesVendas(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of field "Opções - vendas"
			}

			if (getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Operações à termo")) {
				resNeg.setOperacoesTermo(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of field "Operações à termo"
			}

			if (getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Valor das oper. c/ títulos públ. (v. nom.)")) {
				resNeg.setValorOperCTitulosPubl(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of field "Valor das oper.c/ títulos públ. (v. nom.)"
			}

			if (getTextFromCoordinate(fileName, 32, y, 200, 1).equals("Valor das operações")) {
				resNeg.setValorOperacoes(getTextFromCoordinate(fileName, 270, y, 28, 1)); // Value of field "Valor das operações"
				break;
			}

		}

		return resNeg;

	}

	public static HashMap<String, Integer> getPositionOfEspecificacoesDiversas(String fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			if (getTextFromCoordinate(fileName, 32, y, 80, 1).equals("Especificações diversas")) {
				map.put("startPosition", y +1);
			}

			if (getTextFromCoordinate(fileName, 32, y, 80, 1).equals("(*) Observações")) {
				map.put("endPosition", y-1);
				break;
			}
		}

		return map;
	}

	public static EspecificacoesDiversas getValuesOfEspecificacoesDiversas(String fileName){

		HashMap<String, Integer> map = getPositionOfEspecificacoesDiversas(fileName);
		Integer startPosition = map.get("startPosition");
		Integer endPosition = map.get("endPosition");

		EspecificacoesDiversas espDiv = new EspecificacoesDiversas();
		String description = "";
		for (int y = startPosition; y < endPosition; y++) {

			if (getTextFromCoordinate(fileName, 32, y, 268, 1).length() > 1 ) {
				description = description + getTextFromCoordinate(fileName, 32, y, 268, 1) + "; "; // Value of fied "Especificações diversas"
			}
		}

		espDiv.setDescricao(description);

		return espDiv;

	}

	public static HashMap<String, Integer> getPositionOfObservacoes(String fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			if (getTextFromCoordinate(fileName, 32, y, 80, 1).equals("(*) Observações")) {
				map.put("startPosition", y);
			}

			if (getTextFromCoordinate(fileName, 32, y, 80, 1).contains("Capitais e regiões")) {
				map.put("endPosition", y-1);
				break;
			}
		}
		return map;
	}

	public static Observacoes getValuesOfObservacoes(String fileName){

		HashMap<String, Integer> map = getPositionOfObservacoes(fileName);
		Integer startPosition = map.get("startPosition");
		Integer endPosition = map.get("endPosition");

		Observacoes obs = new Observacoes();
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
	
	public static HashMap<String, Integer> getPositionOfResumoFinanceiro(String fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			if (getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Resumo Financeiro")) {
				map.put("startPosition", y);
			}
			
			if (getTextFromCoordinate(fileName, 300, y, 100, 1).contains("Custos Operacionais")) {
				map.put("endPosition", y-1);
				break;
			}
		}
		return map;
	}
	
	public static ResumoFinanceiro getValuesOfResumoFinanceiro(String fileName){

		HashMap<String, Integer> map = getPositionOfResumoFinanceiro(fileName);
		Integer startPosition = map.get("startPosition");
		Integer endPosition = map.get("endPosition");

		ResumoFinanceiro resFin = new ResumoFinanceiro();
		for (int y = startPosition; y < endPosition; y++) {

			if (resFin.getValorLiquidoOperacoes() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Valor líquido das operações")   ) {
				resFin.setValorLiquidoOperacoes(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Valor líquido das operações"
			}
			
			if (resFin.getTaxaLiquidacao() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Taxa de liquidação")   ) {
				resFin.setTaxaLiquidacao(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Taxa deliquidação"
			}

			if (resFin.getTaxaRegistro() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Taxa de Registro")   ) {
				resFin.setTaxaRegistro(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Taxa de Registro"
			}

			if (resFin.getTotalCBLC() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Total CBLC")   ) {
				resFin.setTotalCBLC(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Total CBLC"
			}
			
			if (resFin.getTaxaTermoOpcoes() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Taxa de termo/opções")   ) {
				resFin.setTaxaTermoOpcoes(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Taxa determo/opções"
			}

			if (resFin.getTaxaAna() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Taxa A.N.A.")   ) {
				resFin.setTaxaAna(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Taxa A.N.A."
			}

			if (resFin.getEmolumentos() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Emolumentos")   ) {
				resFin.setEmolumentos(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Emolumentos"
			}

			if (resFin.getTotalBovespaSoma() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Total Bovespa / Soma")   ) {
				resFin.setTotalBovespaSoma(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Total Bovespa / Soma"
			}
		}

		return resFin;
	}
	
	public static HashMap<String, Integer> getPositionOfCustosOperacionais(String fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			if (getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Custos Operacionais")) {
				map.put("startPosition", y);
			}
			
			if (getTextFromCoordinate(fileName, 300, y, 100, 1).contains("Total Custos / Despesas")) {
				map.put("endPosition", y);
				break;
			}
		}
		return map;
	}
	
	public static CustosOperacionais getValuesOfCustosOperacionais(String fileName){

		HashMap<String, Integer> map = getPositionOfCustosOperacionais(fileName);
		Integer startPosition = map.get("startPosition");
		Integer endPosition = map.get("endPosition");

		CustosOperacionais cusOpe = new CustosOperacionais();
		for (int y = startPosition; y <= endPosition; y++) {
			
			if (cusOpe.getTaxaOperacional() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Taxa Operacional") ) {
				cusOpe.setTaxaOperacional(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Taxa Operacional"
			}
			
			if (cusOpe.getExecucao() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Execução") ) {
				cusOpe.setExecucao(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Execução"
			}
			
			if (cusOpe.getTaxaCustodia() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Taxa de Custódia") ) {
				cusOpe.setTaxaCustodia(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Taxa de Custódia"
			}
			
			if (cusOpe.getImpostos() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Impostos") ) {
				cusOpe.setImpostos(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Impostos"
			}

			if (cusOpe.getIrrf() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).contains("I.R.R.F") ) {
				cusOpe.setIrrf(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "I.R.R.F"
			}
			
			if (cusOpe.getOutros() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Outros") ) {
				cusOpe.setOutros(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Outros"
			}

			if (cusOpe.getTotalCustosDespesas() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Total Custos / Despesas") ) {
				cusOpe.setTotalCustosDespesas(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Total Custos / Despesas"
			}
			
		}

		return cusOpe;
	}
	
	public static HashMap<String, Integer> getPositionOfLiquido(String fileName) {

		HashMap<String, Integer> map = new HashMap<>();

		for (int y = 0; y < 1000; y++) {

			if (getTextFromCoordinate(fileName, 300, y, 100, 1).equals("Total Custos / Despesas")) {
				map.put("startPosition", y);
			}
			
			if (getTextFromCoordinate(fileName, 300, y, 100, 1).contains("Líquido para")) {
				map.put("endPosition", y);
				break;
			}
		}
		return map;
	}
	
	public static Liquido getValuesOfLiquido(String fileName){

		HashMap<String, Integer> map = getPositionOfLiquido(fileName);
		Integer startPosition = map.get("startPosition");
		Integer endPosition = map.get("endPosition");

		Liquido liq = new Liquido();
		for (int y = startPosition; y <= endPosition; y++) {
			
			if (liq.getLiquido() == null && getTextFromCoordinate(fileName, 300, y, 100, 1).contains("Líquido para") ) {
				liq.setLiquido(getTextFromCoordinate(fileName, 508, y, 40, 1)); // Value of column "Líquido para"
			}
			
			getTextFromCoordinate(fileName, 508, 646, 40, 1); // valor 34,02
			
			getTextFromCoordinate(fileName, 300, 646, 40, 1);
		}

		return liq;
	}

}
