package com.cesar.trabalho.jpa.core.passagem;

import com.cesar.trabalho.enums.ClassType;
import com.cesar.trabalho.enums.TicketStatus;
import com.cesar.trabalho.jpa.core.assento.AssentoJpa;
import com.cesar.trabalho.jpa.core.reembolso.ReembolsoJpa;
import com.cesar.trabalho.jpa.core.voo.VooJpa;
import com.cesar.trabalho.jpa.customers.cliente.ClienteJpa;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "passagens")
public class PassagemJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime dataCompra;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
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

    public VooJpa getVoo() {
        return voo;
    }

    public void setVoo(VooJpa voo) {
        this.voo = voo;
    }

    public ClienteJpa getCliente() {
        return cliente;
    }

    public void setCliente(ClienteJpa cliente) {
        this.cliente = cliente;
    }

    public AssentoJpa getAssento() {
        return assento;
    }

    public void setAssento(AssentoJpa assento) {
        this.assento = assento;
    }

    private Float preco;
    private ClassType classe;
    private TicketStatus status;

    @OneToMany(mappedBy = "passagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReembolsoJpa> reembolsos = new ArrayList<>();
    @ManyToOne
    private VooJpa voo;
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteJpa cliente;
    @OneToOne
    @JoinColumn(name = "id_assento", nullable = false)
    private AssentoJpa assento;

    @Override
    public String toString() {
        return "PassagemJpa{" +
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