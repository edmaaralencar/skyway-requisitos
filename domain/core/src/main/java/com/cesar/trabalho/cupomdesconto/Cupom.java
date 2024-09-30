package com.cesar.trabalho.cupomdesconto;

import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.time.LocalDate;

@AggregateRoot
public class Cupom {
    @Identity
    private CupomId id;
    private String codigo;

    private Float valorDesconto;
    private LocalDate validade;
    private boolean utilizado;

    public Cupom(String codigo, Float valorDesconto, LocalDate validade, boolean utilizado) {
        this.codigo = codigo;
        this.valorDesconto = valorDesconto;
        this.validade = validade;
        this.utilizado = utilizado;
        this.id = new CupomId();
    }

    public CupomId getId() {
        return id;
    }

    public void setId(CupomId id) {
        this.id = id;
    }

    public Float getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public boolean isUtilizado() {
        return utilizado;
    }

    public void setUtilizado(boolean utilizado) {
        this.utilizado = utilizado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
