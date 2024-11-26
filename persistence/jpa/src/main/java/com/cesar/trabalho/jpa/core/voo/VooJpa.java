package com.cesar.trabalho.jpa.core.voo;

import com.cesar.trabalho.jpa.core.assento.AssentoJpa;
import com.cesar.trabalho.voo.StatusVoo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "voos")
public class VooJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String numero;
    private String origem;
    private String destino;
    @OneToMany(mappedBy = "voo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EscalaJpa> escalas = new ArrayList<>();
    @OneToMany(mappedBy = "voo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AssentoJpa> assentos = new ArrayList<>();
    private LocalDateTime horarioPartida;
    private LocalDateTime horarioChegada;
    private StatusVoo status;

    public VooJpa() {}

    public VooJpa(String numero, String origem, String destino, List<EscalaJpa> escalas, LocalDateTime horarioPartida, LocalDateTime horarioChegada, StatusVoo status, List<AssentoJpa> assentos) {
        this.numero = numero;
        this.origem = origem;
        this.destino = destino;
        this.escalas = escalas;
        this.horarioPartida = horarioPartida;
        this.horarioChegada = horarioChegada;
        this.status = status;
        this.assentos = assentos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public List<EscalaJpa> getEscalas() {
        return escalas;
    }

    public void setEscalas(List<EscalaJpa> escalas) {
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

    public List<AssentoJpa> getAssentos() {
        return assentos;
    }

    public void setAssentos(List<AssentoJpa> assentos) {
        this.assentos = assentos;
    }

    @Override
    public String toString() {
        return "VooJpa{" +
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
