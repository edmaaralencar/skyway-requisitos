package com.cesar.trabalho.jpa.core.cupom;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CupomJpaRepositorio extends JpaRepository<CupomJpa, Long> {
    Optional<CupomJpa> findByCodigo(String codigo);
}
