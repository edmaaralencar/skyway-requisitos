package com.cesar.trabalho.assento;

import com.cesar.trabalho.voo.Voo;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

@AggregateRoot
public class Assento {
    @Identity
    private AssentoId id;
    private String numero;
    private boolean estaDisponivel;

    private Voo voo;

    public Assento(String numero, boolean estaDisponivel, Voo voo) {
        this.numero = numero;
        this.estaDisponivel = estaDisponivel;
        this.voo = voo;
        this.id = new AssentoId();
    }

    public AssentoId getId() {
        return id;
    }

    public void setId(AssentoId id) {
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

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

}
