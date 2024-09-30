package com.cesar.trabalho.models.shared;

import static org.apache.commons.lang3.Validate.isTrue;

import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.Identifier;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@ValueObject
public class Id implements Identifier {
    private final int id;

    public Id(int id) {
        isTrue(id > 0, "Id deve ser maior que zero");
        this.id = id;
    }

    public Id() {
        this.id = 5 + (int)(Math.random() * ((100 - 5) + 1));
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Id) {
            var instance = (Id) o;
            return id == instance.id;
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