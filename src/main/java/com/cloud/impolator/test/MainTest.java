package com.cloud.impolator.test;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class MainTest {


	public static void main( String[] args ) throws IOException {

		String fileName = "C:\\Users\\mfang\\Downloads\\notas.pdf";
		//String fileName = "C:\\Users\\mfang\\Downloads\\manyitens.pdf";
		//String fileName = "C:\\Users\\mfang\\Downloads\\notas - Editado.pdf";

		//String fileName = "C:\\Users\\mfang\\Downloads\\manyitens one page.pdf";

	//	getItensNegociosRealizados(fileName);
		getTamanhoListaItens(fileName);

		/*

		String strNotNeg = getTextFromCoordinate(fileName, 119, 50, 90, 4); // Take the value of  "Nota da negociação", top of page
		System.out.println(strNotNeg);

		// getTextFromCoordinate(fileName, 430, 55, 1000, 4); "Nr. nota Folha Data pregão"
		String strNrNota = getTextFromCoordinate(fileName, 430, 63, 35, 4); // Value of field "Nr. nota" "6497158"
		System.out.println(strNrNota);

		String strFol = getTextFromCoordinate(fileName, 492, 63, 10, 4); // Value of field "Folha" 
		System.out.println(strFol);

		String strDatPre = getTextFromCoordinate(fileName, 520, 63, 32, 4); // Value of field "Data pregão"
		System.out.println(strDatPre);

		String strNomCor = getTextFromCoordinate(fileName, 119, 81, 700, 4); // Value of field "Nome da corretora"
		System.out.println(strNomCor);

		String strAdd = getTextFromCoordinate(fileName, 119, 90, 500, 4); // Value of field "Enderço da corretora"
		System.out.println(strAdd);

		String strTelCor =  getTextFromCoordinate(fileName, 119, 100, 400, 4); // Value of field "Telefone da corretora"
		System.out.println(strTelCor);

		String strSit = getTextFromCoordinate(fileName, 119, 110, 274, 4); // Value of field "Site"
		System.out.println(strSit);

		String strEmaCor = getTextFromCoordinate(fileName, 276, 110, 200, 4); // Valeu of field "E-Mail da corretora"
		System.out.println(strEmaCor);

		String strCnpj = getTextFromCoordinate(fileName, 119, 120, 200, 4); // Value of field "CNPJ da corretora"
		System.out.println(strCnpj);

		String strCarPac = getTextFromCoordinate(fileName, 276, 120, 200, 4); // Value of field "Carta Paciente"
		System.out.println(strCarPac);

		String strOuv = getTextFromCoordinate(fileName, 119, 130, 200, 4); // Value of field "Ouvidoria"
		System.out.println(strOuv);

		String strEmaOuv = getTextFromCoordinate(fileName, 276, 130, 200, 4); // Value of field "E-Mail da ouvidoria"
		System.out.println(strEmaOuv);

		String strCodCli = getTextFromCoordinate(fileName, 70, 152, 200, 4); // Value of field "Cliente"
		System.out.println(strCodCli);

		String strNamCli = getTextFromCoordinate(fileName, 119, 152, 200, 4); // Value of field "Nome do cliente"
		System.out.println(strNamCli);

		String strAddCli = getTextFromCoordinate(fileName, 119, 162, 200, 14); // Value of field "Endereço do cliente"
		System.out.println(strAddCli);

		String strTelCli = getTextFromCoordinate(fileName, 309, 162, 90, 4); // Value of field "Telefone do cliente"
		System.out.println(strTelCli);

		String strCepCid = getTextFromCoordinate(fileName, 309, 172, 90, 4); // Value of field "CEP e cidade do endereço do cliente"
		System.out.println(strCepCid);

		String strCpfCnpjCvmCov = getTextFromCoordinate(fileName, 427, 152, 200, 10); // Value of field "CPF, CNPJ, CVM e COB"
		System.out.println(strCpfCnpjCvmCov);

		String strCliCod = getTextFromCoordinate(fileName, 427, 172, 100, 10); // Value of field "Código Cliente"
		System.out.println(strCliCod);

		String strAss = getTextFromCoordinate(fileName, 520, 172, 70, 10); // Value of field "Assessor"
		System.out.println(strAss);

		String strParDesRep = getTextFromCoordinate(fileName, 70, 190, 900, 4); // Value of fied "Participante destino do repasse"
		System.out.println(strParDesRep);

		String strParDesRepCli = getTextFromCoordinate(fileName, 232, 191, 90, 4); // Value of field "Participante destino do repasse - Cliente"
		System.out.println(strParDesRepCli);

		String strParDesRepVal = getTextFromCoordinate(fileName, 325, 191, 100, 4); // Value of field "Participante destino do repasse - Valor"
		System.out.println(strParDesRepVal);

		String strParDesRepCus = getTextFromCoordinate(fileName, 427, 191, 100, 4); // Value of field "Participante destino do repasse - Custodiante"
		System.out.println(strParDesRepCus);

		String strParDesRepCi = getTextFromCoordinate(fileName, 540, 191, 10, 4); // Value of field "Participante destino do repasse - CI"
		System.out.println(strParDesRepCi);

		String strBanco = getTextFromCoordinate(fileName, 32, 209, 15, 4); // Value of field "Banco"
		System.out.println(strBanco);

		String strAge = getTextFromCoordinate(fileName, 64, 209, 50, 4); // Value of field "Agência"
		System.out.println(strAge);

		String strConCor = getTextFromCoordinate(fileName, 140, 209, 50, 4); // Value of field "Conta Corrênte"
		System.out.println(strConCor);

		String strAci = getTextFromCoordinate(fileName, 253, 209, 50, 4); // Valeu of field "Acionista"
		System.out.println(strAci);

		String strAdm = getTextFromCoordinate(fileName, 325, 209, 50, 4); // Value of field "Adminstrador"
		System.out.println(strAdm);

		String strCom = getTextFromCoordinate(fileName, 427, 209, 50, 4); // Value of field "Complmento"
		System.out.println(strCom);

		String strPVin = getTextFromCoordinate(fileName, 540, 209, 10, 4); // Value of field "P Vin"
		System.out.println(strPVin);


		//getTextFromCoordinate(fileName, 32, 232, 60, 4); // Label "Negócios realizados"
		// Itens do Negócios Realizados

		String strNegReaQ = getTextFromCoordinate(fileName, 30, 254, 10, 4); // Value of column "Negócios realizados - Q"
		System.out.println(strNegReaQ);

		String strNegReaNeg = getTextFromCoordinate(fileName, 42, 254, 45, 4); // Value of column "Negócios realizados - Negociação"
		System.out.println(strNegReaNeg);

		String strNeReaCv = getTextFromCoordinate(fileName, 90, 254, 10, 4); // Value of column "Negócios realizados - C/V"
		System.out.println(strNeReaCv);

		String strNeReaTipMer = getTextFromCoordinate(fileName, 94, 254, 55, 4); // Value of column "Negócios realizados - Tipo mercado"
		System.out.println(strNeReaTipMer);

		String strNegReaPra = getTextFromCoordinate(fileName, 158, 254, 20, 4); // Value of column "Negócios realizados - Prazo"
		System.out.println(strNegReaPra);

		String strNegReaEspTit = getTextFromCoordinate(fileName, 178, 254, 150, 4); // Value of column "Especificação do título"
		System.out.println(strNegReaEspTit);

		String strNeReaObs = getTextFromCoordinate(fileName, 326, 254, 20, 4); // Value of column "Obs"
		System.out.println(strNeReaObs);

		String strNegReaQua = getTextFromCoordinate(fileName, 340, 254, 54, 4); // Value of column "Quantidade"
		System.out.println(strNegReaQua);

		String strNegReaPreAju = getTextFromCoordinate(fileName, 394, 254, 54, 4); // Value of column "Preço Ajuste"
		System.out.println(strNegReaPreAju);

		String strNegReaValOpeAju = getTextFromCoordinate(fileName, 448, 254, 92, 4); // Value of column "Valor Operação / Ajuste"
		System.out.println(strNegReaValOpeAju);

		String strNegReaDc = getTextFromCoordinate(fileName, 540, 264, 20, 4); // Value of column "D/C"
		System.out.println(strNegReaDc);


		String strNrNot = getTextFromCoordinate(fileName, 70, 763, 40, 4); // Take the final value "0800-200-5550 - Rodapé/baseboard"
		System.out.println(strNrNot);	

		 */
	}


	public static String convertPDFToTxt(String filePath) throws IOException {
		byte[] thePDFFileBytes = readFileAsBytes(filePath);
		PDDocument pddDoc = PDDocument.load(thePDFFileBytes);
		PDFTextStripper reader = new PDFTextStripper();
		String pageText = reader.getText(pddDoc);
		pddDoc.close();
		return pageText;
	}

	private static byte[] readFileAsBytes(String filePath) throws IOException {
		FileInputStream inputStream = new FileInputStream(filePath);
		return IOUtils.toByteArray(inputStream);
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

	public static Integer getTamanhoListaItens(String fileName) {

		Integer tamanhoLista = 0;

		for (int y = 0; y < 1000; y++) {
			if (getTextFromCoordinate(fileName, 32, y, 600, 1).contains("Q Negociação C/V Tipo mercado Prazo Especificação do")) {
				
				if (getTextFromCoordinate(fileName, 32, y, 40, 1).equals("Resumo dosNegócios")) {
					tamanhoLista = y;
				}
			}
			
		}
		
		return tamanhoLista;
	}

	public static List<NegociosRealizados> getItensNegociosRealizados(String fileName) {

		List<NegociosRealizados> listNegRea = new ArrayList<NegociosRealizados>();
		NegociosRealizados objectNegRea = new NegociosRealizados();

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

		String encontrouLista = "false";
		String percorerListaItens = "false";
		for (int y = 0; y < 440; y++) {
			if (getTextFromCoordinate(fileName, 32, 235, 600, 1).equals("Negócios realizados")) {

				getTextFromCoordinate(fileName, 32, 246, 600, 1); // Show the names of column "Negócios realizados"

				getTextFromCoordinate(fileName, 32, 257, 600, 1); // Primeira linha com valores
				getTextFromCoordinate(fileName, 32, 267, 600, 1);
				// begin

				if (getTextFromCoordinate(fileName, 32, 246, 600, 1).contains("Q Negociação C/V Tipo mercado Prazo Especificação do") ) {
					encontrouLista = "true";
				}

				if (encontrouLista == "true" && percorerListaItens == "true" ) {

					objectNegRea.setQ(getTextFromCoordinate(fileName, columnQ, y, 10, 1));// Value of column "Negócios realizados - Q"

					objectNegRea.setNegociacao(getTextFromCoordinate(fileName, columnNeg, y, 45, 1)); // Value of column "Negócios realizados - Negociação"

					objectNegRea.setCv(getTextFromCoordinate(fileName, columnCV, y, 10, 1)); // Value of column "Negócios realizados - C/V"

					listNegRea.add(objectNegRea);
				}

				if (getTextFromCoordinate(fileName, columnNeg, y, 45, 1) != "Resumo dosNegócios" && 
						getTextFromCoordinate(fileName, columnTipMer, y, 55, 1) != "" ) {
					percorerListaItens = "true";
				}
				
				if (getTextFromCoordinate(fileName, columnNeg, y, 45, 1).equals("Resumo dosNegócios")) {
					percorerListaItens = "false";
				}



				getTextFromCoordinate(fileName, 32, 235, 600, 1);
			}
			// end

			if (getTextFromCoordinate(fileName, 32, y, 60, 4).toString() != "" && 
					getTextFromCoordinate(fileName, 32, y, 200, 4).toString().length() > 100 ){

				if (getTextFromCoordinate(fileName, 32, y, 60, 4).toString() == "Resumo dosNegócios") {
					System.out.println("Parou a execução");
					break;
				}


				objectNegRea.setQ(getTextFromCoordinate(fileName, columnQ, y, 10, 4));// Value of column "Negócios realizados - Q"

				objectNegRea.setNegociacao(getTextFromCoordinate(fileName, columnNeg, y, 45, 4)); // Value of column "Negócios realizados - Negociação"

				objectNegRea.setCv(getTextFromCoordinate(fileName, columnCV, y, 10, 4)); // Value of column "Negócios realizados - C/V"

				objectNegRea.setTipoMercadoria(getTextFromCoordinate(fileName, columnTipMer, y, 55, 4)); // Value of column "Negócios realizados - Tipo mercado"

				objectNegRea.setPrazo(getTextFromCoordinate(fileName, columnPra, y, 20, 4)); // Value of column "Negócios realizados - Prazo"

				objectNegRea.setEspecificacaoTitulo(getTextFromCoordinate(fileName, columnEspTit, y, 150, 4)); // Value of column "Especificação do título"

				objectNegRea.setObs(getTextFromCoordinate(fileName, columnObs, y, 20, 4)); // Value of column "Obs"

				objectNegRea.setQuantidade(getTextFromCoordinate(fileName, columnQua, y, 54, 4)); // Value of column "Quantidade"

				objectNegRea.setPrecoAjuste(getTextFromCoordinate(fileName, columnPreAju, y, 54, 4)); // Value of column "Preço Ajuste"

				objectNegRea.setValorOperacao(getTextFromCoordinate(fileName, columnValOpeAju, y, 92, 4)); // Value of column "Valor Operação / Ajuste"

				objectNegRea.setDc(getTextFromCoordinate(fileName, columnDC, y, 20, 4)); // Value of column "D/C"

				listNegRea.add(objectNegRea);

			}


		}

		return listNegRea;

	}


}
