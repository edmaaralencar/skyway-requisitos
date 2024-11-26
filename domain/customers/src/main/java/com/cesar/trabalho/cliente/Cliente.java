package com.cesar.trabalho.cliente;

import com.cesar.trabalho.credito.Credito;
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

    public Cliente(ClienteId clienteId, String nome, String cpf, String email, Float saldo) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;

        Fidelidade fidelidade = new Fidelidade("Programa Teste", FidelidadeNivel.BRONZE, Float.valueOf(0));
        this.fidelidade = fidelidade;

        Credito credito = new Credito(saldo);
        this.credito = credito;

        this.id = clienteId;
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

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public Fidelidade getFidelidade() {
        return fidelidade;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", fidelidade=" + fidelidade +
                ", credito=" + credito +
                '}';
    }
}
