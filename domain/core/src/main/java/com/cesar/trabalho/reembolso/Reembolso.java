package com.cesar.trabalho.reembolso;

import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.passagem.Passagem;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.time.LocalDateTime;

@AggregateRoot
public class Reembolso {
    @Identity
    private ReembolsoId id;

    private Cliente cliente;
    private Passagem passagem;
    private Float valor;
    String metodo;
    LocalDateTime dataRecebimento;

    public Reembolso(Cliente cliente, Passagem passagem, Float valor, String metodo, LocalDateTime dataRecebimento) {
        this.cliente = cliente;
        this.passagem = passagem;
        this.valor = valor;
        this.metodo = metodo;
        this.dataRecebimento = dataRecebimento;
        this.id = new ReembolsoId();
    }

    public Reembolso() {}

    public ReembolsoId getId() {
        return id;
    }

    public void setId(ReembolsoId id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public LocalDateTime getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(LocalDateTime dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }
}
