package com.cloud.impolator.api.v1.utils;

public final class RegexUtils {

	public static final String REGEX_DECIMAIS = "([-+]?[0-9]*\\,?[0-9]+([eE][-+]?[0-9]+)?)";

	public static final String REGEX_RESUMO_FINANCEIRO = "Precatório[\\s\\S]*?(\\w+?(?= Financeiro))";

	public static final String REGEX_DATA_PREGAO = "^Data pregão[\\s\\S]*?([0-9]{2}.[0-9]{2}.[0-9]{4})";
	
	public static final String REGEX_NUM_FOLHA = "^Folha[\\s\\S]*?([0-9]{1}|[0-9]{1})";
	
	public static final String REGEX_NUM_NOTA_CORRETAGEM = "^Nr nota[\\s\\S]*?([0-9]{8}|[0-9]{7})";

	public static final String REGEX_VALORES_RESUMO_NEGOCIOS = "([-+]?[0-9]*\\,?[0-9]+([eE][-+]?[0-9]+)?)\\n([-+]?[0-9]*\\,?[0-9]+([eE][-+]?[0-9]+)?)\\n([-+]?[0-9]*\\,?[0-9]+([eE][-+]?[0-9]+)?)\\n([-+]?[0-9]*\\,?[0-9]+([eE][-+]?[0-9]+)?)\\n([-+]?[0-9]*\\,?[0-9]+([eE][-+]?[0-9]+)?)\\n([-+]?[0-9]*\\,?[0-9]+([eE][-+]?[0-9]+)?)\\n([-+]?[0-9]*\\,?[0-9]+([eE][-+]?[0-9]+)?)\\n([-+]?[0-9]*\\,?[0-9]+([eE][-+]?[0-9]+)?)";

	public static final String REGEX_NEGOCIACAO = "^1-(BOVESPA) ([C|V]) (\\w+) (\\w+.+?(?= ON | PN | UNT | CI )) (.*) (\\d{4}|\\d{3}|\\d{2}|\\d{1}) (\\d+?(?=,).\\d+) (\\d+?(?=,).\\d+) ([D|C])$";

	public static final String REGEX_GRUPO_NOTA = "[Negócios realizados][\\s\\S]*?\\w+.?(?=Ouvidoria:)";

	private RegexUtils() {
		// NA
	}

}
