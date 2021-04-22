package com.cloud.impolator.api.v1.business;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cloud.impolator.api.v1.utils.ImportadorUtils;
import com.cloud.impolator.domain.model.ItemNota;
import com.cloud.impolator.domain.model.Nota;

public class Business {
	
	/**
	 * Esse atributo é por conta do método getNotasPorPeriodo
	 */
	List<Nota> notas = new ArrayList<>();

	public List<Nota> extract(final List<String> pFiles, final String password) throws Exception {
		for (String file : pFiles) {
			String content = ImportadorUtils.readFile(file, password);
			ICorretora corretora = CorretoraFactory.factory(ImportadorUtils.getNomeCorretora(content));
			List<Nota> lista = corretora.extract(content);
			
			for(Nota obj : lista) {
				System.out.println(obj);
				notas.add(obj);
			}
		}
		
		return notas;
	}
	
	/**
	 * 
	 * 
	 * Dúvidas:
	 * 1 - vendi no ultimo dia do mês, NC gerada dia seguinte...?
	 * 2 - NC com operações DT e ST... rateio? como calcular?
	 * 
	 * 
	 * @param inicio
	 * @param fim
	 */
	public void calcularDARF(LocalDate inicio, LocalDate fim) {
		List<Nota> notasDoPeriodo = getNotasPorPeriodo(inicio, fim);
		
		BigDecimal totalVendasPeriodo = BigDecimal.ZERO;
		BigDecimal totalComprasPeriodo = BigDecimal.ZERO;
		
		for (Nota nota : notasDoPeriodo) {			
			totalVendasPeriodo = totalVendasPeriodo.add(nota.getResumoNegocio().getVendasVista());
			totalComprasPeriodo = totalComprasPeriodo.add(nota.getResumoNegocio().getComprasVista());
			
			BigDecimal comprasDT = BigDecimal.ZERO;
			BigDecimal vendasDT = BigDecimal.ZERO;
			for (ItemNota negociacao : nota.getItens()) {
				if (negociacao.getObs().equals("D")) {
					if (negociacao.getCompra().booleanValue()) {
						comprasDT = comprasDT.add(negociacao.getValorOperacao());
					} else {
						vendasDT = vendasDT.add(negociacao.getValorOperacao());
					}
				}
			}
			System.out.println(comprasDT);
			System.out.println(vendasDT);
			System.out.println("Lucro DT: R$ " + vendasDT.subtract(comprasDT));
		}

		System.out.println(totalComprasPeriodo);
		System.out.println(totalVendasPeriodo);
		System.out.println(notasDoPeriodo);
	}
	
	private List<Nota> getNotasPorPeriodo(LocalDate inicio, LocalDate fim) {
		List<Nota> notasACalcular = new ArrayList<>();
		for (Nota nota : notas) {
			if (nota.getDataPregao().isAfter(inicio) && nota.getDataPregao().isBefore(fim)) {				
				notasACalcular.add(nota);
			}
		}
		return notasACalcular;
	}

}
