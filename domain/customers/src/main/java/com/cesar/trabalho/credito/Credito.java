package com.cesar.trabalho.credito;

import org.jmolecules.ddd.annotation.ValueObject;

import static org.apache.commons.lang3.Validate.notNull;

@ValueObject
public class Credito {
    private CreditoId id;
    private Float saldo;

    public Credito(Float saldo) {
        notNull(saldo, "Saldo não pode ser nulo");
        this.saldo = saldo;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
}
