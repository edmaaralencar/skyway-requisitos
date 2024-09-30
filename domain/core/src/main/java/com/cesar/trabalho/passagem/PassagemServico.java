package com.cesar.trabalho.passagem;

import com.cesar.trabalho.assento.Assento;
import com.cesar.trabalho.assento.AssentoRepositorio;
import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.cliente.ClienteRepositorio;
import com.cesar.trabalho.cliente.Credito;
import com.cesar.trabalho.models.enums.ClassType;
import com.cesar.trabalho.models.enums.TicketStatus;
import com.cesar.trabalho.voo.Voo;

import java.time.LocalDateTime;

public class PassagemServico {
    private final PassagemRepositorio passagemRepositorio;
    private final AssentoRepositorio assentoRepositorio;
    private final ClienteRepositorio clienteRepositorio;


    public PassagemServico(PassagemRepositorio passagemRepositorio, AssentoRepositorio assentoRepositorio, ClienteRepositorio clienteRepositorio) {
        this.passagemRepositorio = passagemRepositorio;
        this.assentoRepositorio = assentoRepositorio;
        this.clienteRepositorio = clienteRepositorio;

    }

    public Passagem reservarVoo(Voo voo, Assento assento, Cliente cliente, Float preco, ClassType classe) throws Exception {
        if (!assento.isEstaDisponivel()) {
            throw new Exception("Assento ocupado");
        }

        if (preco > cliente.getCredito().getSaldo()) {
            throw new Exception("Saldo insuficiente");
        }

        Passagem passagem = new Passagem(
                LocalDateTime.now(),
                preco,
                classe,
                TicketStatus.ATIVA,
                voo,
                cliente,
                assento
        );

        assento.setEstaDisponivel(false);

        Credito credito = new Credito(cliente.getCredito().getSaldo());
        credito.setSaldo(credito.getSaldo() - preco);
        cliente.setCredito(credito);

        passagemRepositorio.salvar(passagem);
        assentoRepositorio.salvar(assento);
        clienteRepositorio.salvar(cliente);

        return passagem;
    }
}
