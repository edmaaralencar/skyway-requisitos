package com.cesar.trabalho.models;

import com.cesar.trabalho.enums.BaggageType;
import com.cesar.trabalho.models.shared.Id;
import org.jmolecules.ddd.annotation.Entity;

import java.math.BigDecimal;

@Entity
public class Baggage {
    private Id id;
    private BaggageType baggageType;
    private BigDecimal volume;

}
