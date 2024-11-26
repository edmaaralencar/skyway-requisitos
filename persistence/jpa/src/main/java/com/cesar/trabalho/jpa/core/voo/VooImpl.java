package com.cesar.trabalho.jpa.core.voo;

import com.cesar.trabalho.jpa.JpaMapeador;
import com.cesar.trabalho.voo.Voo;
import com.cesar.trabalho.voo.VooRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class VooImpl implements VooRepositorio {
    @Autowired
    public VooJpaRepositorio repositorio;
    @Autowired
    private JpaMapeador mapeador = JpaMapeador.getInstance();
    @Override
    public Voo salvar(Voo voo) {
        VooJpa vooJpa = mapeador.map(voo, VooJpa.class);

        repositorio.save(vooJpa);

        return voo;
    }
    public Optional<Voo> encontrarPorId(Long id) {
        Optional<VooJpa> vooJpa = repositorio.findById((long)id);

        return Optional.of(mapeador.map(vooJpa, Voo.class));
    }
}
