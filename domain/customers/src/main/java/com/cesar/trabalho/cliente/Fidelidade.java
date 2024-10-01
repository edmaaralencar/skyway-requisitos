package com.cesar.trabalho.cliente;

import org.jmolecules.ddd.annotation.ValueObject;

import static org.apache.commons.lang3.Validate.notNull;

@ValueObject
public class Fidelidade {
    private String nomePrograma;
    private FidelidadeNivel nivel;
    private Float pontosAcumulados;

    public Fidelidade(String nomePrograma, FidelidadeNivel nivel, Float pontosAcumulados) {
        notNull(nomePrograma, "Nome do programa não pode ser nulo");
        notNull(nivel, "Nível não pode ser nulo");
        notNull(pontosAcumulados, "Pontos acumulados não pode ser nulo");
        this.nomePrograma = nomePrograma;
        this.nivel = nivel;
        this.pontosAcumulados = pontosAcumulados;
    }
}
