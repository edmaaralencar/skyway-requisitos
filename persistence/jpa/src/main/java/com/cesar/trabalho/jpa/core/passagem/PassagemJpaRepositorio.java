package com.cesar.trabalho.jpa.core.passagem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassagemJpaRepositorio extends JpaRepository<PassagemJpa, Long> {
    List<PassagemJpa> findManyByClienteId(Long id);
}
