package com.cesar.trabalho.jpa.customers.cliente;

import com.cesar.trabalho.jpa.core.reembolso.ReembolsoJpa;
import com.cesar.trabalho.jpa.core.voo.EscalaJpa;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clientes")
public class ClienteJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private String email;
    private Float saldo;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReembolsoJpa> reembolsos = new ArrayList<>();

    public ClienteJpa() {}

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public String getEmail() {
        return email;
    }

    public Float getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "ClienteJpa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
