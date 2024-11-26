package com.cesar.trabalho.steps;

import com.cesar.trabalho.*;
import com.cesar.trabalho.credito.Credito;
import com.cesar.trabalho.assento.Assento;
import com.cesar.trabalho.assento.AssentoRepositorio;
import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.cliente.ClienteRepositorio;
import com.cesar.trabalho.enums.ClassType;
import com.cesar.trabalho.passagem.Passagem;
import com.cesar.trabalho.passagem.PassagemRepositorio;
import com.cesar.trabalho.passagem.PassagemServico;
import com.cesar.trabalho.voo.StatusVoo;
import com.cesar.trabalho.voo.Voo;
import com.cesar.trabalho.voo.VooRepositorio;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;

public class ReservarVooDefinition {
    private final PassagemRepositorio passagemRepositorio = new MemoriaPassagemRepositorio();
    private final AssentoRepositorio assentoRepositorio = new MemoriaAssentoRepositorio();
    private final ClienteRepositorio clienteRepositorio = new MemoriaClienteRepositorio();
    private final VooRepositorio vooRepositorio = new MemoriaVooRepositorio();

    private final PassagemServico passagemServico = new PassagemServico(passagemRepositorio, assentoRepositorio, clienteRepositorio, vooRepositorio);
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
            StatusVoo.CONFIRMADO,
            Float.valueOf("400")
    );

    private Cliente cliente = new Cliente(
            "John Doe",
            "123.456.789-00",
            "johndoe@example.com",
            Float.valueOf(20)
    );

    @Given("que o passageiro visualiza o mapa de assentos e seleciona um assento específico que está {string}")
    public void visualizarESelcionarAssento(String disponibilidade) {
        boolean disponivel = disponibilidade.equals("disponível");

        assento = new Assento("A1",  disponivel, voo);
    }


    @When("o passageiro {string} a reserva do assento")
    public void confirmarAssentoSelecionado(String acao) {
        Float saldoCliente = acao.contains("saldo") ? Float.valueOf(100) : Float.valueOf(500);

        Credito credito = new Credito(saldoCliente);
        cliente.setCredito(credito);

        try {
            passagem = this.passagemServico.reservarVoo(voo, assento, cliente, ClassType.ECONOMICA, Optional.empty());
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
