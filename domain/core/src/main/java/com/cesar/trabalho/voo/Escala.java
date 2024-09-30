package com.cesar.trabalho.voo;

import org.jmolecules.ddd.annotation.ValueObject;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.Validate.notNull;

@ValueObject
public class Escala {
    private String aeroporto;
    private LocalDateTime horarioPartida;
    private LocalDateTime horarioChegada;

    public Escala(String aeroporto, LocalDateTime horarioPartida, LocalDateTime horarioChegada) {
        notNull(aeroporto, "Aeroporto não pode ser nulo");
        notNull(horarioPartida, "Horário de partida não pode ser nulo");
        notNull(horarioChegada, "Horário de chegada não pode ser nulo");
        this.aeroporto = aeroporto;
        this.horarioPartida = horarioPartida;
        this.horarioChegada = horarioChegada;
    }

    public String getAeroporto() {
        return aeroporto;
    }

    public void setAeroporto(String aeroporto) {
        this.aeroporto = aeroporto;
    }

    public LocalDateTime getHorarioPartida() {
        return horarioPartida;
    }

    public void setHorarioPartida(LocalDateTime horarioPartida) {
        this.horarioPartida = horarioPartida;
    }

    public LocalDateTime getHorarioChegada() {
        return horarioChegada;
    }

    public void setHorarioChegada(LocalDateTime horarioChegada) {
        this.horarioChegada = horarioChegada;
    }
}
