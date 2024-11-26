package com.cesar.trabalho.jpa.core.assento;

import com.cesar.trabalho.assento.Assento;
import com.cesar.trabalho.assento.AssentoRepositorio;
import com.cesar.trabalho.jpa.JpaMapeador;
import com.cesar.trabalho.voo.VooId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AssentoImpl implements AssentoRepositorio {
    @Autowired
    private AssentoJpaRepositorio repositorio;

    @Autowired
    private JpaMapeador mapeador = JpaMapeador.getInstance();

    @Override
    public Assento salvar(Assento assento) {
        AssentoJpa assentoJpa = mapeador.map(assento, AssentoJpa.class);

        repositorio.save(assentoJpa);

        return assento;
    }

    public Optional<Assento> encontrarPorId(Long id) {
        Optional<AssentoJpa> assentoJpa = repositorio.findById((long)id);

        return Optional.of(mapeador.map(assentoJpa, Assento.class));
    }

    @Override
    public Assento encontrarPorNumeroEVoo(String numero, VooId vooId) {
        return null;
    }

    @Override
    public List<Assento> encontrarMuitosPorVoo(VooId vooId) {
        return null;
    }
}
