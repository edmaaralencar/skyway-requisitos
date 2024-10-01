package com.cesar.trabalho.reembolso;

import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.Identifier;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.isTrue;

@ValueObject
public class ReembolsoId implements Identifier {
    private final int id;

    public ReembolsoId(int id) {
        isTrue(id > 0, "Id deve ser maior que zero");
        this.id = id;
    }

    public ReembolsoId() {
        this.id = 5 + (int)(Math.random() * ((100 - 5) + 1));
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof ReembolsoId) {
            var instance = (ReembolsoId) o;
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
