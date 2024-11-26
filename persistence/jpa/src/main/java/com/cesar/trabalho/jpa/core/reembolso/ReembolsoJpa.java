package com.cesar.trabalho.jpa.core.reembolso;

import com.cesar.trabalho.jpa.core.passagem.PassagemJpa;
import com.cesar.trabalho.jpa.customers.cliente.ClienteJpa;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reembolsos")
public class ReembolsoJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ClienteJpa cliente;
    @ManyToOne
    private PassagemJpa passagem;
    private Float valor;
    private String metodo;
    private LocalDateTime dataRecebimento;

    public ReembolsoJpa() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteJpa getCliente() {
        return cliente;
    }

    public void setCliente(ClienteJpa cliente) {
        this.cliente = cliente;
    }

    public PassagemJpa getPassagem() {
        return passagem;
    }

    public void setPassagem(PassagemJpa passagem) {
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

    @Override
    public String toString() {
        return "ReembolsoJpa{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", passagem=" + passagem +
                ", valor=" + valor +
                ", metodo='" + metodo + '\'' +
                ", dataRecebimento=" + dataRecebimento +
                '}';
    }
}
