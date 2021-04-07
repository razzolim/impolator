package com.cloud.impolator.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cloud.impolator.domain.model.Nota;


@Repository
public interface NotaRepository  extends CustomJpaRepository<Nota, Long>, JpaSpecificationExecutor<Nota> {
	
	Optional<Nota> findByCodigo(Long id);
	
	List<Nota> findAll();

}
