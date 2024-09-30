package com.cesar.trabalho.models;

import com.cesar.trabalho.models.shared.Id;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.List;
import java.util.UUID;

@ValueObject
public class FidelityProgram {
    private Id id;
    private String programName;
    private int pointsAccumulated;
    private List<DiscountCoupon> discountsAvailable;
}
