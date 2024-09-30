package com.cesar.trabalho.models;

import org.jmolecules.ddd.annotation.Entity;

@Entity
public class Seat {
    private String id;
    private String seatNumber;
    private boolean isAvailable;

    private Flight flight;

    public Seat(String seatNumber, boolean isAvailable, Flight flight) {
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
        this.flight = flight;
        this.id = seatNumber + "-" + flight.getId().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
