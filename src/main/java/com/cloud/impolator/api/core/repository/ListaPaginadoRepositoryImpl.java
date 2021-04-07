package com.cloud.impolator.api.core.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.cloud.impolator.api.core.repository.filter.FiltroPesquisa;
import com.cloud.impolator.api.core.repository.filter.FiltroPesquisa.ArgumentoPesquisa;
import com.cloud.impolator.api.core.repository.filter.FiltroPesquisa.CondicaoPesquisa;
import com.cloud.impolator.api.core.repository.filter.FiltroPesquisa.OrdemPesquisa;


@Repository
public class ListaPaginadoRepositoryImpl<T, R, ID> {

	@PersistenceContext
	protected EntityManager manager;

	public Page<R> listarPaginado(Class<T> entityClass, Class<R> responseClass, FiltroPesquisa filtro, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<R> criteria = builder.createQuery(responseClass);

		Root<T> root = criteria.from(entityClass);
		
		definirCamposConsulta(builder, criteria, root);

		Predicate[] predicates = criarRestricoes(filtro, builder, root);
		criteria.where(predicates);

		adicionarOrdenacao(builder, criteria, root, filtro);

		TypedQuery<R> query = manager.createQuery(criteria);

		adicionarRestricaoDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(entityClass, filtro));
	}

	protected void definirCamposConsulta(CriteriaBuilder builder, CriteriaQuery<R> criteria, Root<T> root) {
	}

	protected void adicionarOrdenacao(CriteriaBuilder builder, CriteriaQuery<R> criteria, Root<T> root,
			FiltroPesquisa filtro) {

		if (filtro == null || filtro.getOrdens().isEmpty()) {
			return;
		}

		List<Order> ordenacao = new ArrayList<>();

		for (OrdemPesquisa ordem : filtro.getOrdens()) {

			if (ordem.isAsc()) {
				ordenacao.add(builder.asc(root.get(ordem.getNomeColuna())));
			} else {
				ordenacao.add(builder.desc(root.get(ordem.getNomeColuna())));
			}
		}

		criteria.orderBy(ordenacao);
	}

	protected Predicate[] criarRestricoes(FiltroPesquisa filtro, CriteriaBuilder builder, Root<T> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (filtro != null) {

			for (ArgumentoPesquisa argumento : filtro.getArgumentos()) {
				predicates.add(getPredicate(builder, root, argumento));
			}

			for (FiltroPesquisa filtroOR : filtro.getAgrupamentosOR()) {
				Predicate[] predicatesOR = criarRestricoes(filtroOR, builder, root);
				if (predicatesOR.length > 0) {
					predicates.add(builder.or(predicatesOR));
				}
			}
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	protected Predicate getPredicate(CriteriaBuilder builder, Root<T> root, ArgumentoPesquisa argumento) {

		if (CondicaoPesquisa.LIKE.equals(argumento.getCondicao())) {
			return builder.like(builder.lower(root.get(argumento.getNomeColuna())),
					"%" + argumento.getValor().toString().toLowerCase() + "%");
		}

		else if (CondicaoPesquisa.EQUALS.equals(argumento.getCondicao())) {
			return builder.equal(root.get(argumento.getNomeColuna()), argumento.getValor());
		}

		return null;
	}

	protected Long total(Class<T> entityClass, FiltroPesquisa filtro) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);

		Root<T> root = criteria.from(entityClass);

		Predicate[] predicates = criarRestricoes(filtro, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}

	protected void adicionarRestricaoDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
}
