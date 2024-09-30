package com.cesar.trabalho.cliente;

import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.Identifier;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.isTrue;

@ValueObject
public class CreditoId implements Identifier {
    private final int id;

    public CreditoId(int id) {
        isTrue(id > 0, "Id deve ser maior que zero");
        this.id = id;
    }

    public CreditoId() {
        this.id = 5 + (int)(Math.random() * ((100 - 5) + 1));
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof CreditoId) {
            var instance = (CreditoId) o;
            return id == instance.getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
