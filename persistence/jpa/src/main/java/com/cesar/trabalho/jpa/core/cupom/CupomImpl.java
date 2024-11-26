package com.cesar.trabalho.jpa.core.cupom;

import com.cesar.trabalho.cupomdesconto.Cupom;
import com.cesar.trabalho.cupomdesconto.CupomRepositorio;
import com.cesar.trabalho.jpa.JpaMapeador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CupomImpl implements CupomRepositorio {
    @Autowired
    private CupomJpaRepositorio repositorio;
    @Autowired
    private JpaMapeador mapeador = JpaMapeador.getInstance();
    @Override
    public Cupom salvar(Cupom cupom) {
        CupomJpa cupomJpa = mapeador.map(cupom, CupomJpa.class);

        repositorio.save(cupomJpa);

        return cupom;
    }

    @Override
    public Optional<Cupom> encontrarPorCodigo(String codigo) {
        Optional<CupomJpa> cupomJpa = repositorio.findByCodigo(codigo);

        return Optional.of(mapeador.map(cupomJpa, Cupom.class));
    }
}
