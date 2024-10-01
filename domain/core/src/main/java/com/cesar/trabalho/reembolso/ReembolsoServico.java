package com.cesar.trabalho.reembolso;

import com.cesar.trabalho.assento.Assento;
import com.cesar.trabalho.assento.AssentoRepositorio;
import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.cliente.ClienteRepositorio;
import com.cesar.trabalho.credito.Credito;
import com.cesar.trabalho.enums.ClassType;
import com.cesar.trabalho.enums.TicketStatus;
import com.cesar.trabalho.passagem.Passagem;
import com.cesar.trabalho.passagem.PassagemRepositorio;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReembolsoServico {
    private final PassagemRepositorio passagemRepositorio;
    private final AssentoRepositorio assentoRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final ReembolsoRepositorio reembolsoRepositorio;


    public ReembolsoServico(PassagemRepositorio passagemRepositorio, AssentoRepositorio assentoRepositorio, ClienteRepositorio clienteRepositorio, ReembolsoRepositorio reembolsoRepositorio) {
        this.passagemRepositorio = passagemRepositorio;
        this.assentoRepositorio = assentoRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.reembolsoRepositorio = reembolsoRepositorio;
    }

    public Reembolso solicitarReembolso(Cliente cliente, Passagem passagem) throws Exception {
        Float valorReembolso = calcularReembolso(passagem);

        Reembolso reembolso = new Reembolso(
                cliente,
                passagem,
                valorReembolso.floatValue(),
                "Cartão de Crédito", // Exemplo de método de pagamento
                LocalDateTime.now().plusDays(7)  // Data simulada de recebimento
        );

        passagem.setStatus(TicketStatus.REEMBOLSADA);

        Assento assento = passagem.getAssento();
        assento.setEstaDisponivel(true);

        this.reembolsoRepositorio.salvar(reembolso);
        this.passagemRepositorio.salvar(passagem);
        this.assentoRepositorio.salvar(assento);

        if (valorReembolso == 0) {
            throw new Exception("Não há reembolso disponível para este cancelamento.");
        }

        cliente.getCredito().setSaldo(cliente.getCredito().getSaldo() + valorReembolso);

        this.clienteRepositorio.salvar(cliente);

        return reembolso;
    }

    private Float calcularReembolso(Passagem passagem) {
        ClassType classType = passagem.getClasse();
        LocalDateTime dataVoo = passagem.getVoo().getHorarioPartida();
        LocalDateTime dataAtual = LocalDateTime.now();

        long diasParaOVoo = dataAtual.until(dataVoo, java.time.temporal.ChronoUnit.DAYS);
        Float valorPassagem = passagem.getPreco();

        if (diasParaOVoo > 30) {
            return valorPassagem;
        } else if (diasParaOVoo > 7) {
            return valorPassagem * Float.valueOf("0.7");
        } else if (diasParaOVoo > 1) {
            return valorPassagem * Float.valueOf("0.5");
        } else if (classType == ClassType.EXECUTIVA) {
            return Float.valueOf(0);
        }

        return Float.valueOf(0);
    }

    private boolean aprovarReembolso(BigDecimal valorReembolso) {
        return valorReembolso.compareTo(BigDecimal.ZERO) > 0;
    }
}
