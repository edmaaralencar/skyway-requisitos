package com.cesar.trabalho;

import com.cesar.trabalho.models.FlightTicket;
import com.cesar.trabalho.models.shared.Id;
import com.cesar.trabalho.repositories.FlightTicketRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryFlightTicketRepository implements FlightTicketRepository {
    private final Map<String, FlightTicket> flightTickets = new HashMap<>();

    @Override
    public FlightTicket save(FlightTicket flightTicket) {
        this.flightTickets.put(flightTicket.getId().toString(), flightTicket);
        return flightTicket;
    }

    @Override
    public FlightTicket findById(Id id) {
        return this.flightTickets.get(id.toString());
    }
}
