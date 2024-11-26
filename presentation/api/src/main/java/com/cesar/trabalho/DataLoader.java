package com.cesar.trabalho;

import com.cesar.trabalho.jpa.core.assento.AssentoJpa;
import com.cesar.trabalho.jpa.core.cupom.CupomJpa;
import com.cesar.trabalho.jpa.core.cupom.CupomJpaRepositorio;
import com.cesar.trabalho.jpa.core.voo.EscalaJpa;
import com.cesar.trabalho.jpa.core.voo.EscalaJpaRepositorio;
import com.cesar.trabalho.jpa.core.voo.VooJpa;
import com.cesar.trabalho.jpa.core.voo.VooJpaRepositorio;
import com.cesar.trabalho.voo.StatusVoo;
import com.cesar.trabalho.voo.Voo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DataLoader {

    private VooJpaRepositorio vooJpaRepositorio;
    private EscalaJpaRepositorio escalaJpaRepositorio;
    private CupomJpaRepositorio cupomJpaRepositorio;

    @Autowired
    public DataLoader(VooJpaRepositorio vooJpaRepositorio, EscalaJpaRepositorio escalaJpaRepositorio, CupomJpaRepositorio cupomJpaRepositorio) {
        this.vooJpaRepositorio = vooJpaRepositorio;
        this.escalaJpaRepositorio = escalaJpaRepositorio;
        this.cupomJpaRepositorio = cupomJpaRepositorio;
        loadFirstFlight();
        loadSecondFlight();
        loadCupon();
    }

    private void loadCupon() {
        CupomJpa cupomJpa = new CupomJpa();

        cupomJpa.setValidade(LocalDateTime.now().plusDays(1).toLocalDate());
        cupomJpa.setValorDesconto(Float.valueOf("30.00"));
        cupomJpa.setCodigo("1234");

        cupomJpaRepositorio.save(cupomJpa);
    }

    private void loadFirstFlight() {
        List<EscalaJpa> escalas = new ArrayList<>();
        List<AssentoJpa> assentos = new ArrayList<>();

        VooJpa vooJpa = new VooJpa(
                "BA5678",
                "Recife",
                "Brasília",
                Collections.emptyList(),
                LocalDateTime.of(2024, 11, 1, 22, 0),
                LocalDateTime.of(2024, 11, 2, 14, 0),
                StatusVoo.CONFIRMADO,
                Collections.emptyList(),
                Float.valueOf("500.00")
        );

        EscalaJpa escalaJpa = new EscalaJpa();
        escalaJpa.setAeroporto("Aeroporto Guarulhos");
        escalaJpa.setHorarioChegada(LocalDateTime.of(2024, 11, 1, 22, 0));
        escalaJpa.setHorarioPartida(LocalDateTime.of(2024, 11, 1, 22, 0));
        escalaJpa.setVoo(vooJpa);

        AssentoJpa assentoJpa = new AssentoJpa();
        assentoJpa.setEstaDisponivel(true);
        assentoJpa.setNumero("A-1");
        assentoJpa.setVoo(vooJpa);
        AssentoJpa assentoJpa2 = new AssentoJpa();
        assentoJpa2.setEstaDisponivel(true);
        assentoJpa2.setNumero("A-2");
        assentoJpa2.setVoo(vooJpa);

        escalas.add(escalaJpa);
        assentos.add(assentoJpa);
        assentos.add(assentoJpa2);

        vooJpa.setEscalas(escalas);
        vooJpa.setAssentos(assentos);

        vooJpaRepositorio.save(vooJpa);
    }

    private void loadSecondFlight() {
        List<AssentoJpa> assentos = new ArrayList<>();

        VooJpa vooJpa = new VooJpa(
                "BA5678",
                "Recife",
                "São Paulo",
                Collections.emptyList(),
                LocalDateTime.of(2024, 11, 1, 22, 0),
                LocalDateTime.of(2024, 11, 2, 14, 0),
                StatusVoo.CONFIRMADO,
                Collections.emptyList(),
                Float.valueOf("600.00")
        );


        AssentoJpa assentoJpa = new AssentoJpa();
        assentoJpa.setEstaDisponivel(true);
        assentoJpa.setNumero("A-1");
        assentoJpa.setVoo(vooJpa);
        AssentoJpa assentoJpa2 = new AssentoJpa();
        assentoJpa2.setEstaDisponivel(true);
        assentoJpa2.setNumero("A-2");
        assentoJpa2.setVoo(vooJpa);

        assentos.add(assentoJpa);
        assentos.add(assentoJpa2);

        vooJpa.setAssentos(assentos);

        vooJpaRepositorio.save(vooJpa);
    }

}