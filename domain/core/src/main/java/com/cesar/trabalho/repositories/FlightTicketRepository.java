package com.cesar.trabalho.repositories;

import com.cesar.trabalho.models.FlightTicket;
import com.cesar.trabalho.models.shared.Id;

import java.util.UUID;

public interface FlightTicketRepository {
    FlightTicket save(FlightTicket flightTicket);
    FlightTicket findById(Id id);
}