package com.cesar.trabalho.repositories;

import com.cesar.trabalho.models.Seat;
import com.cesar.trabalho.models.shared.Id;

import java.util.List;
import java.util.UUID;

public interface SeatRepository {
    Seat findBySeatNumberAndFlight(String seatNumber, Id flightId);
    List<Seat> findManyByFlight(Id flightId);
    Seat save(Seat seat);
}
