package com.cesar.trabalho.models;

import com.cesar.trabalho.models.shared.Id;
import org.jmolecules.ddd.annotation.ValueObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@ValueObject
public class DiscountCoupon {
    private Id id;
    private String code;
    private BigDecimal discountValue;
    private LocalDate expiresAt;

    public DiscountCoupon(String code, BigDecimal discountValue, LocalDate expiresAt) {
        this.code = code;
        this.discountValue = discountValue;
        this.expiresAt = expiresAt;
        this.id = new Id();
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public LocalDate getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDate expiresAt) {
        this.expiresAt = expiresAt;
    }
}
