package com.cesar.trabalho.jpa.core.assento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssentoJpaRepositorio extends JpaRepository<AssentoJpa, Long> {
    List<AssentoJpa> findManyByVooId(Long vooId);
}
