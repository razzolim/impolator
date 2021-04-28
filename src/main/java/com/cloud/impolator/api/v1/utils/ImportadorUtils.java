package com.cloud.impolator.api.v1.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.cloud.impolator.api.v1.constant.ClearConstants;
import com.cloud.impolator.api.v1.constant.XPConstants;
import com.cloud.impolator.domain.model.ItemNota;
import com.cloud.impolator.domain.model.Nota;
import com.cloud.impolator.domain.model.ResumoFinanceiro;
import com.cloud.impolator.domain.model.ResumoNegocio;


public final class ImportadorUtils {

	private static final List<String> CORRETORAS = Arrays.asList(ClearConstants.NOME_CORRETORA,
			XPConstants.NOME_CORRETORA);

	private static String notaAtual;

	private ImportadorUtils() {
		// NA
	}

	/**
	 * Recebe um content e extrai os dados da(s) nota(s), retornando uma lista de
	 * notas.
	 * 
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static List<Nota> extractDataFromNota(String content) {

		Pattern grupoNota = Pattern.compile(RegexUtils.REGEX_GRUPO_NOTA, Pattern.MULTILINE);
		Matcher mtGrupoNota = grupoNota.matcher(content.replace(".", ""));

		Map<Integer, Nota> mapNotas = new HashMap<>();
		Nota nota = null;

		while (mtGrupoNota.find()) {
			notaAtual = mtGrupoNota.group();

			Pattern patrNumNota = Pattern.compile(RegexUtils.REGEX_NUM_NOTA_CORRETAGEM, Pattern.MULTILINE);
			Matcher mtNumNota = patrNumNota.matcher(notaAtual);

			if (mtNumNota.find()) {
				Integer numero = Integer.valueOf(mtNumNota.group(1));

				if (!mapNotas.containsKey(numero)) {
					mapNotas.put(numero, new Nota());
				}

				nota = mapNotas.get(numero);
				nota.setNumNota(numero); 
			} else {
				continue;
			}

			Pattern patrFolha = Pattern.compile(RegexUtils.REGEX_NUM_FOLHA, Pattern.MULTILINE);
			Matcher mtNumFolha = patrFolha.matcher(notaAtual);
			
			if (mtNumFolha.find()) {
				nota.setFolha(Integer.valueOf(mtNumFolha.group(1)));
			}
			
			Pattern patrDataPregao = Pattern.compile(RegexUtils.REGEX_DATA_PREGAO, Pattern.MULTILINE);
			Matcher mtDataPregao = patrDataPregao.matcher(notaAtual);

			if (mtDataPregao.find()) {
				nota.setDataPregao(LocalDate.parse(mtDataPregao.group(1), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			}

			preencherNegociacoes(nota);

			preencherResumoDosNegocios(nota);

			preencherResumofinanceiro(nota);

		}

		return Arrays.asList(mapNotas.values().toArray(new Nota[0]));
	}

	private static void preencherNegociacoes(Nota nota) {

		Pattern p = Pattern.compile(RegexUtils.REGEX_NEGOCIACAO, Pattern.MULTILINE);
		Matcher mtNegociacoes = p.matcher(notaAtual);

		nota.setItens(createNegociacao(mtNegociacoes, notaAtual));

	}


	
	private static List<ItemNota> createNegociacao(Matcher mtNegociacoes, String notaAtual) {
		
		ArrayList<ItemNota> listNegRea = new ArrayList<ItemNota>();
		
		Pattern p = Pattern.compile(RegexUtils.REGEX_NEGOCIACAO, Pattern.MULTILINE);
		mtNegociacoes = p.matcher(notaAtual);

		while (mtNegociacoes.find()) {
			
			// TODO não tem mapeado o Q na itemNota
			ItemNota negociacao = new ItemNota();
			negociacao.setBolsaValores(mtNegociacoes.group(1));
			negociacao.setCompra(mtNegociacoes.group(2).equals("C"));
			negociacao.setTipoMercado(mtNegociacoes.group(3));
			negociacao.setEspecificacaoTitulo(mtNegociacoes.group(4).trim());

			setTipoEObservacao(mtNegociacoes.group(5), negociacao);

			negociacao.setQuantidade(Integer.valueOf(mtNegociacoes.group(6)));
			negociacao.setPrecoAjuste(new BigDecimal(mtNegociacoes.group(7).replace(",", ".")));
			negociacao.setValorOperacao(new BigDecimal(mtNegociacoes.group(8).replace(",", ".")));
			negociacao.setDebitoCredito(mtNegociacoes.group(9));
			
			listNegRea.add(negociacao);
		}

		return listNegRea;
	}


	private static void setTipoEObservacao(String group, ItemNota negociacao) {

		String[] values = group.split(" ");

		StringBuilder tipo = new StringBuilder("");
		String obs = "";
		for (int i = 0; i < values.length; i++) {
			if (values[i].contains("#") || values[i].contains("D")) {
				obs = values[i];
			} else {
				tipo.append(values[i] + " ");
			}
		}
		negociacao.setObs(obs);
		negociacao.setPrazo(tipo.toString().trim());
	}

	private static void preencherResumoDosNegocios(Nota nota) {
		ResumoNegocio resumoNegocios = new ResumoNegocio();

		Pattern patrValores = Pattern.compile(RegexUtils.REGEX_VALORES_RESUMO_NEGOCIOS, Pattern.MULTILINE);
		Matcher mtResumo = patrValores.matcher(notaAtual);

		if (mtResumo.find()) {
			// o regex está pegando os campos na ordem 1, 3, 5, 7... está correto assim.
			// TODO futuramente ajustar o regex pra pegar na ordem correta
			resumoNegocios.setDebentures(NumberUtils.parseToBigDecimal(mtResumo.group(1)));
			resumoNegocios.setVendasVista(NumberUtils.parseToBigDecimal(mtResumo.group(3)));
			resumoNegocios.setComprasVista(NumberUtils.parseToBigDecimal(mtResumo.group(5)));
			resumoNegocios.setOpcoesCompras(NumberUtils.parseToBigDecimal(mtResumo.group(7)));
			resumoNegocios.setOpcoesVendas(NumberUtils.parseToBigDecimal(mtResumo.group(9)));
			resumoNegocios.setOperacoesTermo(NumberUtils.parseToBigDecimal(mtResumo.group(11)));
			resumoNegocios.setValorOperCTitulosPubl(NumberUtils.parseToBigDecimal(mtResumo.group(13)));
			resumoNegocios.setValorOperacoes(NumberUtils.parseToBigDecimal(mtResumo.group(15)));
		}

		nota.setResumoNegocio(resumoNegocios);
	}

	private static void preencherResumofinanceiro(Nota nota) {
		Pattern patternGroup = Pattern.compile(RegexUtils.REGEX_RESUMO_FINANCEIRO, Pattern.MULTILINE);
		Matcher matcherResumo = patternGroup.matcher(notaAtual);

		ResumoFinanceiro financeiro = new ResumoFinanceiro();

		while (matcherResumo.find()) {
			String valores = matcherResumo.group();
			Pattern pattern = Pattern.compile(RegexUtils.REGEX_DECIMAIS, Pattern.MULTILINE);
			Matcher mtDecimais = pattern.matcher(valores);

			int i = 0;
			while (mtDecimais.find()) {

				switch (i) {
				case 0:
					financeiro.setTotalcblc(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 1:
					financeiro.setValorLiquidoOperacoes(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 2:
					financeiro.setTaxaLiquidacao(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 3:
					financeiro.setTaxaRegistro(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 4:
					financeiro.setTotalBovespaSoma(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 5:
					financeiro.setTaxaTermoOpcoes(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 6:
					financeiro.setTaxaAna(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 7:
					financeiro.setEmolumentos(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 8:
					financeiro.setTotalCustosDespesas(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 9:
					financeiro.setTaxaOperacional(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 10:
					financeiro.setExecucao(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 11:
					financeiro.setTaxaCustodia(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 12:
					financeiro.setImpostos(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 13:
					financeiro.setIrrf(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 15:
					financeiro.setOutros(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				case 16:
					financeiro.setLiquidoPara(NumberUtils.parseToBigDecimal(mtDecimais.group()));
					break;
				default:
				}
				i++;
			}
			nota.setResumoFinanceiro(financeiro);
		}
	}

	public static final String readFile(String pFile, final String password) throws Exception {
		StringBuilder content = new StringBuilder();
		byte[] bytes = Base64.getDecoder().decode(pFile);

		try (PDDocument document = PDDocument.load(bytes, password)) {
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition(true);

			PDFTextStripper tStripper = new PDFTextStripper();

			String pdfFileInText = tStripper.getText(document);

			// split by whitespace
			String[] lines = pdfFileInText.split("\\r?\\n");

			for (String line : lines) {
				content.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return content.toString();
	}

	public static final String getNomeCorretora(final String content) throws Exception {
		for (String corretora : CORRETORAS) {
			if (content.contains(corretora)) {
				return corretora;
			}
		}
		return null;
	}
}
