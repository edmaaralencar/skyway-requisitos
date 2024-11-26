package com.cesar.trabalho.passagem;

import com.cesar.trabalho.assento.Assento;
import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.enums.ClassType;
import com.cesar.trabalho.enums.TicketStatus;
import com.cesar.trabalho.voo.Voo;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.time.LocalDateTime;

@AggregateRoot
public class Passagem {
    @Identity
    private PassagemId id;

    private LocalDateTime dataCompra;
    private Float preco;
    private ClassType classe;
    private TicketStatus status;

    private Voo voo;
    private Cliente cliente;
    private Assento assento;

    public Passagem() {}

    public Passagem(LocalDateTime dataCompra, Float preco, ClassType classe, TicketStatus status, Voo voo, Cliente cliente, Assento assento) {
        this.dataCompra = dataCompra;
        this.preco = preco;
        this.classe = classe;
        this.status = status;
        this.voo = voo;
        this.cliente = cliente;
        this.assento = assento;
        this.id = new PassagemId();
    }

    public Passagem(LocalDateTime dataCompra, Float preco, ClassType classe, TicketStatus status, Voo voo, Cliente cliente) {
        this.dataCompra = dataCompra;
        this.preco = preco;
        this.classe = classe;
        this.status = status;
        this.voo = voo;
        this.cliente = cliente;
        this.id = new PassagemId();
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Assento getAssento() {
        return assento;
    }

    public void setAssento(Assento assento) {
        this.assento = assento;
    }

    public PassagemId getId() {
        return id;
    }

    public void setId(PassagemId id) {
        this.id = id;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public ClassType getClasse() {
        return classe;
    }

    public void setClasse(ClassType classe) {
        this.classe = classe;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Passagem{" +
                "id=" + id +
                ", dataCompra=" + dataCompra +
                ", preco=" + preco +
                ", classe=" + classe +
                ", status=" + status +
                ", voo=" + voo +
                ", cliente=" + cliente +
                ", assento=" + assento +
                '}';
    }
}
