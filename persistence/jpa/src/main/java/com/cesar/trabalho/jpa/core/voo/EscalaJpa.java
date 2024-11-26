package com.cesar.trabalho.jpa.core.voo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "voo_escalas")
public class EscalaJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String aeroporto;
    private LocalDateTime horarioPartida;
    private LocalDateTime horarioChegada;

    @ManyToOne
    @JoinColumn(name = "voo_id")
    private VooJpa voo;

    public VooJpa getVoo() {
        return voo;
    }

    public void setVoo(VooJpa voo) {
        this.voo = voo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
