package com.cesar.trabalho.services;

import com.cesar.trabalho.models.Seat;
import com.cesar.trabalho.models.shared.Id;
import com.cesar.trabalho.repositories.SeatRepository;

import java.util.List;

public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> listAll(Id flightId) {
        return this.seatRepository.findManyByFlight(flightId);
    }
}
