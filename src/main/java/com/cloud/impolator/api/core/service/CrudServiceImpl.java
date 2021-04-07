package com.cloud.impolator.api.core.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.impolator.api.core.repository.HistoricoInclusao;
import com.cloud.impolator.api.core.repository.ListaPaginadoRepositoryImpl;
import com.cloud.impolator.api.core.repository.filter.FiltroPesquisa;


public abstract class CrudServiceImpl<T extends HistoricoInclusao, ID> implements CrudService<T, ID> {

	@Autowired
	protected JpaRepository<T, ID> repository;

	@Autowired
	protected ListaPaginadoRepositoryImpl<T, T, ID> repositoryPaginado;

	protected Class<T> entityClass;

	public CrudServiceImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public List<T> listar() {
		return repository.findAll();
	}

	@Override
	public Page<T> listarPaginado(FiltroPesquisa filtroPesquisa, Pageable pageable) {
		return repositoryPaginado.listarPaginado(entityClass, entityClass, filtroPesquisa, pageable);
	}

	@Override
	public T salvar(T entidade) {
		antesSalvar(entidade);
		preencherHistoricoInclusao(entidade);
		return repository.save(entidade);
	}

	protected void antesSalvar(T entidade) {
	}

	@Override
	public void apagar(ID id) {
		repository.deleteById(id);
	}

	@Override
	public T buscarPorID(ID id) {
		Optional<T> entidadeSalva = repository.findById(id);
		if (!entidadeSalva.isPresent()) {
			String mensagem = entityClass + " by ID '"+id+"' ";
			throw new EmptyResultDataAccessException(mensagem, 1);
		}
		return entidadeSalva.get();
	}

	protected void preencherHistoricoInclusao(T entidade) {

		if (entidade instanceof HistoricoInclusao) {

			HistoricoInclusao entidadeHistorico = (HistoricoInclusao) entidade;

			if (entidadeHistorico.isNew()) {
				entidade.setInclusao(new Date());
			} else {
				entidade.setAtualizacao(new Date());
			}
		}
	}
}
