package com.cesar.trabalho.steps;

import com.cesar.trabalho.*;
import com.cesar.trabalho.assento.Assento;
import com.cesar.trabalho.assento.AssentoRepositorio;
import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.cliente.ClienteRepositorio;
import com.cesar.trabalho.cliente.Credito;
import com.cesar.trabalho.models.enums.ClassType;
import com.cesar.trabalho.models.enums.TicketStatus;
import com.cesar.trabalho.passagem.Passagem;
import com.cesar.trabalho.passagem.PassagemRepositorio;
import com.cesar.trabalho.passagem.PassagemServico;
import com.cesar.trabalho.voo.StatusVoo;
import com.cesar.trabalho.voo.Voo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.Assert.*;

public class TrocarDeVooDefinition {
    private final PassagemRepositorio passagemRepositorio = new MemoriaPassagemRepositorio();
    private final AssentoRepositorio assentoRepositorio = new MemoriaAssentoRepositorio();
    private final ClienteRepositorio clienteRepositorio = new MemoriaClienteRepositorio();

    private final PassagemServico passagemServico = new PassagemServico(passagemRepositorio, assentoRepositorio, clienteRepositorio);

    private Float precoVoo = Float.valueOf(400);
    private Exception reservationException;
    private Assento assento;
    private Passagem passagem;
    private Voo voo = new Voo(
            "BA5678",
            "London",
            "Tokyo",
            Collections.emptyList(),
            LocalDateTime.of(2024, 11, 1, 22, 0),
            LocalDateTime.of(2024, 11, 2, 14, 0),
            StatusVoo.CONFIRMADO
    );

    private Voo novoVoo = new Voo(
            "BA5600",
            "Recife",
            "Þernambuco",
            Collections.emptyList(),
            LocalDateTime.of(2024, 11, 1, 22, 0),
            LocalDateTime.of(2024, 11, 2, 14, 0),
            StatusVoo.CONFIRMADO
    );

    private Cliente cliente = new Cliente(
            "John Doe",
            "123.456.789-00",
            "johndoe@example.com",
            Float.valueOf(20)
    );

    private Passagem passagem;

    @Given("que o passageiro deseja alterar o voo")
    public void selecionarVooParaAlterar(String disponibilidade) {
        passagem = new Passagem(
                LocalDateTime.now(),
                Float.valueOf(1000),
                ClassType.ECONOMICA,
                TicketStatus.ATIVA,
                voo,
                cliente
        );
    }

    @When("o passageiro escolhe um voo {string}")
    public void selecionarVoo(String acao) {
        if (acao.contains("alternativo")) {

        }
    }

    @Then("o sistema calcula a diferença de tarifa")
    public void systemCalculateDifference() {}

    @Then("o passageiro seleciona um novo assento no voo alterado")
    public void passengerSelectNewSeat() {}




    @When("o passageiro {string} a reserva do assento")
    public void confirmarAssentoSelecionado(String acao) {
        Float saldoCliente = acao.contains("saldo") ? Float.valueOf(100) : Float.valueOf(500);

        Credito credito = new Credito(saldoCliente);
        cliente.setCredito(credito);

        try {
            passagem = this.passagemServico.reservarVoo(voo, assento, cliente, precoVoo, ClassType.ECONOMICA);
        } catch (Exception e) {
            reservationException = e;
        }
    }

    @Then("a reserva do assento é {string}")
    public void resultadoDoAssentoConfirmado(String response) {
        if (response.equals("confirmada")) {
            assertNotNull(passagem);
            assertEquals(assento, passagem.getAssento());
            assertFalse(assento.isEstaDisponivel());
            assertNull(reservationException);
            assertEquals(cliente.getCredito().getSaldo(), Float.valueOf(100));
        } else if (response.contains("saldo")) {
            if (reservationException.getMessage().contains("saldo")) {
                assertEquals(cliente.getCredito().getSaldo(), Float.valueOf(500));
            } else {
                assertEquals(cliente.getCredito().getSaldo(), Float.valueOf(100));
            }
            assertNull(passagem);
            assertNotNull(reservationException);
        }
    }

    @And("o assento selecionado fica indisponível para outros passageiros")
    public void assentoSelecionadoSeTornaIndisponível() {
        assertFalse(assento.isEstaDisponivel());
    }

    @And("o passageiro é notificado que o assento já está ocupado")
    public void passageiroENotificadoDoAssentoOcupado() {
        assertEquals("Assento ocupado", reservationException.getMessage());
    }

    @And("o passageiro é notificado que não tem saldo suficiente para a reserva")
    public void passageiroENotificadoDeSaldoInsuficiente() {
        assertEquals("Saldo insuficiente", reservationException.getMessage());
    }
}
