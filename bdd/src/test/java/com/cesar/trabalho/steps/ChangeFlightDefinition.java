package com.cesar.trabalho.steps;

import com.cesar.trabalho.InMemoryCustomerRepository;
import com.cesar.trabalho.InMemoryFlightTicketRepository;
import com.cesar.trabalho.InMemorySeatRepository;
import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.models.Customer;
import com.cesar.trabalho.models.Flight;
import com.cesar.trabalho.models.FlightTicket;
import com.cesar.trabalho.models.Seat;
import com.cesar.trabalho.models.enums.ClassType;
import com.cesar.trabalho.models.enums.FidelityLevel;
import com.cesar.trabalho.models.enums.TicketStatus;
import com.cesar.trabalho.passagem.Passagem;
import com.cesar.trabalho.repositories.CustomerRepository;
import com.cesar.trabalho.repositories.FlightTicketRepository;
import com.cesar.trabalho.repositories.SeatRepository;
import com.cesar.trabalho.services.FlightTicketService;
import com.cesar.trabalho.voo.StatusVoo;
import com.cesar.trabalho.voo.Voo;
import io.cucumber.java.en.Given;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class ChangeFlightDefinition {
    private final FlightTicketRepository flightTicketRepository = new InMemoryFlightTicketRepository();
    private final SeatRepository seatRepository = new InMemorySeatRepository();
    private final CustomerRepository customerRepository = new InMemoryCustomerRepository();

    private final FlightTicketService flightTicketService = new FlightTicketService(flightTicketRepository, seatRepository, customerRepository);

    private BigDecimal flightPrice = new BigDecimal("400");
    private Exception reservationException;
    private Seat seat;
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

    @Given("que o passageiro deseja alterar o voo")
    public void customerWantsToChangeFlight() {

    }
}
