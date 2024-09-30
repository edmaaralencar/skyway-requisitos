package com.cesar.trabalho.cliente;

import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

@AggregateRoot
public class Cliente {
    @Identity
    private ClienteId id;
    private String nome;
    private String cpf;
    private String email;
    private Fidelidade fidelidade;
    private Credito credito;

    public Cliente(String nome, String cpf, String email, Float saldo) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;

        Fidelidade fidelidade = new Fidelidade("Programa Teste", FidelidadeNivel.BRONZE, Float.valueOf(0));
        this.fidelidade = fidelidade;

        Credito credito = new Credito(saldo);
        this.credito = credito;

        this.id = new ClienteId();
    }

    public ClienteId getId() {
        return id;
    }

    public void setId(ClienteId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Fidelidade getFidelidade() {
        return fidelidade;
    }

    public void setFidelidade(Fidelidade fidelidade) {
        this.fidelidade = fidelidade;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }
}
