package com.cesar.trabalho.steps;

import com.cesar.trabalho.*;
import com.cesar.trabalho.assento.Assento;
import com.cesar.trabalho.assento.AssentoRepositorio;
import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.cliente.ClienteRepositorio;
import com.cesar.trabalho.enums.ClassType;
import com.cesar.trabalho.enums.TicketStatus;
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
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import static org.junit.Assert.*;

public class TrocarVooDefinition {
    private final PassagemRepositorio passagemRepositorio = new MemoriaPassagemRepositorio();
    private final AssentoRepositorio assentoRepositorio = new MemoriaAssentoRepositorio();
    private final ClienteRepositorio clienteRepositorio = new MemoriaClienteRepositorio();
    private final VooRepositorio vooRepositorio = new MemoriaVooRepositorio();
    private final PassagemServico passagemServico = new PassagemServico(passagemRepositorio, assentoRepositorio, clienteRepositorio, vooRepositorio);

    private Voo novoVoo;
    private Passagem passagem;
    private Cliente cliente = new Cliente("John Doe", "123.456.789-00", "johndoe@example.com", Float.valueOf(700));
    private Assento assentoSelecionado;
    private Exception exception;
    private Float precoDiferenca;

    @Given("que o passageiro deseja alterar o voo")
    public void passageiroDesejaAlterarVoo() {
        Voo voo = new Voo("AB1234", "New York", "London", Collections.emptyList(), LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(5).plusHours(7), StatusVoo.CONFIRMADO, Float.valueOf("300"));
        Assento assento = new Assento("A1", true, voo);

        passagem = new Passagem(
                LocalDateTime.now(),
                Float.valueOf(300),
                ClassType.ECONOMICA,
                TicketStatus.ATIVA,
                voo,
                cliente,
                assento
        );
    }

    @When("o passageiro escolhe um voo")
    public void passageiroEscolheVoo() {
        novoVoo = new Voo("BA5678", "London", "Tokyo", Collections.emptyList(), LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(14), StatusVoo.CONFIRMADO, Float.valueOf("500"));
        assentoSelecionado = new Assento("B2", true, novoVoo);
    }

    @And("o voo é de classe {string}")
    public void vooEClasse(String classe) {
        ClassType classType = ClassType.valueOf(classe.toUpperCase());
        passagem.setClasse(classType);
    }

    @And("a alteração é feita com {string}")
    public void alteracaoComTempo(String tempo) {
        long horasParaPartida = tempo.equals("menos de 24 horas") ? 12 : 80;
        novoVoo.setHorarioPartida(LocalDateTime.now().plus(horasParaPartida, ChronoUnit.HOURS));
    }

    @Then("o sistema calcula as taxas")
    public void calculaTarifaETaxas() {
        try {
            precoDiferenca = Float.valueOf(500);  // Preço do novo voo
            passagem = passagemServico.trocarVoo(passagem, novoVoo, assentoSelecionado, cliente, passagem.getClasse(), LocalDateTime.now());
        } catch (Exception e) {
            exception = e;
        }
    }

    @And("o sistema confirma a alteração {string}")
    public void passageiroSelecionaNovoAssento(String tipo) {
        String comCusto = String.valueOf(tipo.contains("taxas"));

        if (Boolean.valueOf(comCusto)) {
            assertEquals(cliente.getCredito().getSaldo(), Float.valueOf(400));
        } else {
            assertEquals(cliente.getCredito().getSaldo(), Float.valueOf(500));
        }

        assertNotNull(passagem);
        assertEquals(assentoSelecionado.getId(), passagem.getAssento().getId());
        assertNull(exception);
        assertFalse(assentoSelecionado.isEstaDisponivel());
    }
}