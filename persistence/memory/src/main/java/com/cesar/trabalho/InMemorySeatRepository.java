package com.cesar.trabalho;

import com.cesar.trabalho.models.FlightTicket;
import com.cesar.trabalho.models.Seat;
import com.cesar.trabalho.models.shared.Id;
import com.cesar.trabalho.repositories.SeatRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySeatRepository implements SeatRepository {
    private final Map<String, Seat> seats = new HashMap<>();

    private String generateKey(String seatNumber, Id flightId) {
        return seatNumber + "-" + flightId.toString();
    }

    @Override
    public Seat findBySeatNumberAndFlight(String seatNumber, Id flightId) {
        String key = generateKey(seatNumber, flightId);
        return seats.get(key);
    }

    @Override
    public List<Seat> findManyByFlight(Id flightId) {
        List<Seat> result = new ArrayList<>();
        for (Seat seat : seats.values()) {
            if (seat.getFlight().getId().equals(flightId)) {
                result.add(seat);
            }
        }
        return result;
    }

    @Override
    public Seat save(Seat seat) {
        seats.put(seat.getId().toString(), seat);
        return seat;
    }
}
