package com.cesar.trabalho.voo;

import com.cesar.trabalho.assento.Assento;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.time.LocalDateTime;
import java.util.List;

@AggregateRoot
public class Voo {
    @Identity
    private VooId id;
    private String numero;
    private String origem;
    private String destino;
    private List<Escala> escalas;
    private List<Assento> assentos;
    private LocalDateTime horarioPartida;
    private LocalDateTime horarioChegada;
    private StatusVoo status;

    public Voo() {}

    public Voo(String numero, String origem, String destino, List<Escala> escalas, LocalDateTime horarioPartida, LocalDateTime horarioChegada, StatusVoo status) {
        this.numero = numero;
        this.origem = origem;
        this.destino = destino;
        this.escalas = escalas;
        this.horarioPartida = horarioPartida;
        this.horarioChegada = horarioChegada;
        this.status = status;
        this.id = new VooId();
    }

    public VooId getId() {
        return id;
    }

    public void setId(VooId id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public List<Escala> getEscalas() {
        return escalas;
    }

    public void setEscalas(List<Escala> escalas) {
        this.escalas = escalas;
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

    public StatusVoo getStatus() {
        return status;
    }

    public void setStatus(StatusVoo status) {
        this.status = status;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Assento> getAssentos() {
        return assentos;
    }

    public void setAssentos(List<Assento> assentos) {
        this.assentos = assentos;
    }

    @Override
    public String toString() {
        return "Voo{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", escalas=" + escalas +
                ", assentos=" + assentos +
                ", horarioPartida=" + horarioPartida +
                ", horarioChegada=" + horarioChegada +
                ", status=" + status +
                '}';
    }
}
