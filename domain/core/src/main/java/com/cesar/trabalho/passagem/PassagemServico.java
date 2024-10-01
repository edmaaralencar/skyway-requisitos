package com.cesar.trabalho.passagem;

import com.cesar.trabalho.credito.Credito;
import com.cesar.trabalho.assento.Assento;
import com.cesar.trabalho.assento.AssentoRepositorio;
import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.cliente.ClienteRepositorio;
import com.cesar.trabalho.enums.ClassType;
import com.cesar.trabalho.enums.TicketStatus;
import com.cesar.trabalho.voo.Voo;

import java.time.Duration;
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

    public Passagem trocarVoo(Passagem passagem, Voo novoVoo, Assento assento, Cliente cliente, Float preco, ClassType classe, LocalDateTime changeRequestTime) throws Exception {
        if (!assento.getVoo().equals(novoVoo)) {
            throw new Exception("Esse assento não é desse voo");
        }

        if (!assento.isEstaDisponivel()) {
            throw new Exception("Assento indisponível");
        }

        Float priceDifference = preco - passagem.getPreco();

        Float classeTaxa = calculateClassFee(classe);

        Float tempoTaxa = calculateTimeChangeFee(passagem.getVoo().getHorarioChegada(), changeRequestTime);

        Float taxas = priceDifference + classeTaxa + tempoTaxa;

        if (taxas == 0) {
            passagem.setAssento(assento);
            passagem.setVoo(novoVoo);
            assento.setEstaDisponivel(false);
        } else {
            if (taxas > cliente.getCredito().getSaldo()) {
                throw new Exception("Saldo insuficiente");
            }

            Credito novoCredito = new Credito(cliente.getCredito().getSaldo() - taxas);
            passagem.setAssento(assento);
            cliente.setCredito(novoCredito);
            assento.setEstaDisponivel(false);
            passagem.setVoo(novoVoo);
        }

        this.clienteRepositorio.salvar(cliente);
        this.passagemRepositorio.salvar(passagem);
        this.assentoRepositorio.salvar(assento);

        return passagem;
    }

    private Float calculateClassFee(ClassType classType) {
        switch (classType) {
            case EXECUTIVA:
                return Float.valueOf(100);
            default: // ECONOMICA
                return Float.valueOf(0);
        }
    }

    private Float calculateTimeChangeFee(LocalDateTime flightDeparture, LocalDateTime changeRequestTime) {
        long hoursUntilDeparture = Duration.between(changeRequestTime, flightDeparture).toHours();
        if (hoursUntilDeparture < 24) {
            return Float.valueOf(150);
        } else if (hoursUntilDeparture < 72) {
            return Float.valueOf(50);
        } else {
            return Float.valueOf(0);
        }
    }
}
