package com.cesar.trabalho.steps;

import com.cesar.trabalho.MemoriaCupomRepositorio;
import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.cupomdesconto.Cupom;
import com.cesar.trabalho.cupomdesconto.CupomRepositorio;
import com.cesar.trabalho.cupomdesconto.CupomServico;
import com.cesar.trabalho.models.enums.ClassType;
import com.cesar.trabalho.models.enums.TicketStatus;
import com.cesar.trabalho.passagem.Passagem;
import com.cesar.trabalho.voo.StatusVoo;
import com.cesar.trabalho.voo.Voo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.Assert.*;

public class ValidarCupomDeDescontoDefinition {
    private final CupomRepositorio cupomRepositorio = new MemoriaCupomRepositorio();
    private final CupomServico cupomServico = new CupomServico(cupomRepositorio);
    private LocalDate now = LocalDate.now();
    private LocalDate oneDayAfterNow = now.plusDays(1);
    private String code = "1234";
    private String validCode = "1234";
    private Cupom cupom = new Cupom(validCode, Float.valueOf(100), now, false);

    Exception couponValidationException;

    private Voo voo = new Voo(
            "BA5678",
            "London",
            "Tokyo",
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

    private Passagem passagem = new Passagem(
            LocalDateTime.now(),
            Float.valueOf(1000),
            ClassType.ECONOMICA,
            TicketStatus.ATIVA,
            voo,
            cliente
    );

    @Given("que o passageiro possui um cupom de desconto {string}")
    public void usuarioTemCupom(String status) {
        if (status.equals("expirado")) {
            cupom.setValidade(now.minusDays(1));
        } else if (status.equals("inválido")) {
            code = "código-inválido";
        }

        this.cupomRepositorio.salvar(cupom);
    }

    @When("o passageiro insere o código do cupom durante a compra")
    public void validateCode() {
        try {
            cupom = this.cupomServico.validarCodigo(code);
        } catch (Exception e) {
            System.out.println("Exception When: " + e);
            couponValidationException = e;
            cupom = null;
        }
    }

    @Then("o sistema valida o cupom e aplica o desconto corretamente")
    public void codeIsCorrect() {
        passagem.setPreco(passagem.getPreco() - cupom.getValorDesconto());

        assertNotNull(cupom);
        assertEquals(Float.valueOf(900), passagem.getPreco());
    }

    @Then("o sistema rejeita o cupom e exibe uma mensagem informando que o cupom {string}")
    public void errorInCodeValidation(String error) {
        assertEquals(error.equals("expirou") ? "Código expirado" : "Código não existe", couponValidationException.getMessage());
        assertNull(cupom);
    }
}
