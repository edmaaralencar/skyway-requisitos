package com.cesar.trabalho.services;

import com.cesar.trabalho.models.Customer;
import com.cesar.trabalho.models.Flight;
import com.cesar.trabalho.models.FlightTicket;
import com.cesar.trabalho.models.Seat;
import com.cesar.trabalho.enums.ClassType;
import com.cesar.trabalho.enums.TicketStatus;
import com.cesar.trabalho.repositories.CustomerRepository;
import com.cesar.trabalho.repositories.FlightTicketRepository;
import com.cesar.trabalho.repositories.SeatRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FlightTicketService {
    private final FlightTicketRepository flightTicketRepository;
    private final SeatRepository seatRepository;
    private final CustomerRepository customerRepository;

    public FlightTicketService(FlightTicketRepository flightTicketRepository, SeatRepository seatRepository, CustomerRepository customerRepository) {
        this.flightTicketRepository = flightTicketRepository;
        this.seatRepository = seatRepository;
        this.customerRepository = customerRepository;
    }

    public FlightTicket reserveFlight(Flight flight, Seat seat, Customer customer, BigDecimal price) throws Exception {
        if (!seat.isAvailable()) {
            throw new Exception("Assento ocupado");
        }

        if (price.compareTo(customer.getBalance()) > 0) {
            throw new Exception("Saldo insuficiente");
        }

        FlightTicket flightTicket = new FlightTicket(
                UUID.randomUUID().toString(),
                price,
                ClassType.ECONOMICA,
                TicketStatus.ATIVA,
                LocalDate.now(),
                customer,
                flight,
                seat
        );

        seat.setAvailable(false);

        customer.setBalance(customer.getBalance().subtract(price));

        flightTicketRepository.save(flightTicket);
        seatRepository.save(seat);
        customerRepository.save(customer);

        return flightTicket;
    }

    public FlightTicket changeFlight(FlightTicket flightTicket, Flight newFlight, Seat selectedSeat, Customer customer, BigDecimal price) throws Exception {
        if (!selectedSeat.getFlight().getId().equals(newFlight.getId())) {
            throw new Exception("Esse assento não é desse voo");
        }

        if (!selectedSeat.isAvailable()) {
            throw new Exception("Assento indisponível");
        }

        BigDecimal priceDifference = price.subtract(flightTicket.getPrice());

        if (priceDifference.compareTo(new BigDecimal("0")) == 0) {
            flightTicket.setFlight(newFlight);
            selectedSeat.setAvailable(false);
        } else {
            if (priceDifference.compareTo(customer.getBalance()) > 0) {
                throw new Exception("Saldo insuficiente");
            }

            customer.setBalance(customer.getBalance().subtract(priceDifference));
            selectedSeat.setAvailable(false);
        }

        this.customerRepository.save(customer);
        this.flightTicketRepository.save(flightTicket);
        this.seatRepository.save(selectedSeat);

        return flightTicket;
    }
}
