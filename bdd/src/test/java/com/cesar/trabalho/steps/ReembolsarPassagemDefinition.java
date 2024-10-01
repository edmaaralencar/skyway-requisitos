package com.cesar.trabalho.steps;

import com.cesar.trabalho.MemoriaAssentoRepositorio;
import com.cesar.trabalho.MemoriaClienteRepositorio;
import com.cesar.trabalho.MemoriaPassagemRepositorio;
import com.cesar.trabalho.MemoriaReembolsoRepositorio;
import com.cesar.trabalho.assento.Assento;
import com.cesar.trabalho.assento.AssentoRepositorio;
import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.cliente.ClienteRepositorio;
import com.cesar.trabalho.enums.ClassType;
import com.cesar.trabalho.enums.TicketStatus;
import com.cesar.trabalho.passagem.Passagem;
import com.cesar.trabalho.passagem.PassagemRepositorio;
import com.cesar.trabalho.reembolso.Reembolso;
import com.cesar.trabalho.reembolso.ReembolsoRepositorio;
import com.cesar.trabalho.reembolso.ReembolsoServico;
import com.cesar.trabalho.voo.StatusVoo;
import com.cesar.trabalho.voo.Voo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

public class ReembolsarPassagemDefinition {

    private final ReembolsoRepositorio reembolsoRepositorio = new MemoriaReembolsoRepositorio();
    private final ClienteRepositorio clienteRepositorio = new MemoriaClienteRepositorio();
    private final PassagemRepositorio passagemRepositorio = new MemoriaPassagemRepositorio();
    private final AssentoRepositorio assentoRepositorio = new MemoriaAssentoRepositorio();
    private final ReembolsoServico reembolsoServico = new ReembolsoServico(passagemRepositorio, assentoRepositorio, clienteRepositorio, reembolsoRepositorio);
    private Cliente cliente;
    private Passagem passagem;
    private Reembolso reembolso;
    private Exception reembolsoException;

    // Cenário: Passageiro deseja cancelar a passagem dentro ou fora do prazo
    @Given("que o passageiro deseja cancelar a passagem {string}")
    public void passageiroDesejaCancelarPassagem(String status) {
        Boolean dentroDoPrazo = status.contains("dentro");
        // Inicializa um cliente e uma passagem fictícia
        cliente = new Cliente("John Doe", "123.456.789-00", "johndoe@example.com", Float.valueOf(100));
        Voo voo = new Voo("AB1234", "New York", "London", Collections.emptyList(), LocalDateTime.now().plusDays(dentroDoPrazo ? 5 : -5), LocalDateTime.now().plusDays(dentroDoPrazo ? 5 : -5).plusHours(7), StatusVoo.CONFIRMADO);
        Assento assento = new Assento("A1", true, voo);

        passagem = new Passagem(
                LocalDateTime.now(),
                Float.valueOf(300),
                dentroDoPrazo ? ClassType.EXECUTIVA : ClassType.ECONOMICA,
                TicketStatus.ATIVA,
                voo,
                cliente,
                assento
        );
    }

    @When("o passageiro solicita o cancelamento")
    public void passageiroSolicitaCancelamento() {
        try {
            reembolso = reembolsoServico.solicitarReembolso(cliente, passagem);
        } catch (Exception e) {
            reembolsoException = e;
        }
    }

    @Then("o sistema calcula o valor do reembolso com base na política de cancelamento")
    public void sistemaCalculaReembolso() {
        assertNotNull(reembolso);
        assertTrue(reembolso.getValor() > 0);
    }

    @And("o sistema credita o valor aprovado na conta do passageiro")
    public void sistemaCreditaValor() {
        assertNotNull(reembolso);
        assertEquals(passagem.getStatus(), TicketStatus.REEMBOLSADA);
        assertEquals(passagem.getAssento().isEstaDisponivel(), true);
        assertEquals(100 + reembolso.getValor(), cliente.getCredito().getSaldo(), 0.01);
    }

    @Then("o sistema informa que não há reembolso disponível")
    public void sistemaInformaSemReembolso() {
        assertNotNull(reembolsoException);
        assertEquals("Não há reembolso disponível para este cancelamento.", reembolsoException.getMessage());
    }

    @And("o cancelamento é concluído sem restituição")
    public void cancelamentoSemRestituicao() {
        assertNull(reembolso);
        assertEquals(passagem.getStatus(), TicketStatus.REEMBOLSADA);
        assertEquals(passagem.getAssento().isEstaDisponivel(), true);
        assertEquals(100.0f, cliente.getCredito().getSaldo(), 0.01); // Confirmando que o saldo do cliente não foi alterado
    }
}
