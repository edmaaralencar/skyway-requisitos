package com.cesar.trabalho.jpa.core.assento;

import com.cesar.trabalho.jpa.core.voo.VooJpa;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "assentos")
public class AssentoJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String numero;
    private boolean estaDisponivel;

    @ManyToOne
    @JoinColumn(name = "voo_id") // Define a chave estrangeira
    private VooJpa voo;

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

    public boolean isEstaDisponivel() {
        return estaDisponivel;
    }

    public void setEstaDisponivel(boolean estaDisponivel) {
        this.estaDisponivel = estaDisponivel;
    }

    public VooJpa getVoo() {
        return voo;
    }

    public void setVoo(VooJpa voo) {
        this.voo = voo;
    }

    @Override
    public String toString() {
        return "AssentoJpa{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", estaDisponivel=" + estaDisponivel +
                ", voo=" + voo +
                '}';
    }
}