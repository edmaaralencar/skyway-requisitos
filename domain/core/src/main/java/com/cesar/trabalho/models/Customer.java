package com.cesar.trabalho.models;

import com.cesar.trabalho.models.enums.FidelityLevel;
import com.cesar.trabalho.models.shared.Id;
import org.jmolecules.ddd.annotation.AggregateRoot;

import java.math.BigDecimal;
import java.util.UUID;

@AggregateRoot
public class Customer {
    private Id id;
    private String name;
    private String cpf;
    private String email;
    private BigDecimal balance;
    private FidelityLevel fidelityLevel;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Customer(String name, String cpf, String email, BigDecimal balance, FidelityLevel fidelityLevel) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.balance = balance;
        this.fidelityLevel = fidelityLevel;
        this.id = new Id();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public FidelityLevel getFidelityLevel() {
        return fidelityLevel;
    }

    public void setFidelityLevel(FidelityLevel fidelityLevel) {
        this.fidelityLevel = fidelityLevel;
    }
}
