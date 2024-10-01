package com.cesar.trabalho.models;

import com.cesar.trabalho.enums.ClassType;
import com.cesar.trabalho.enums.TicketStatus;
import com.cesar.trabalho.models.shared.Id;
import org.jmolecules.ddd.annotation.AggregateRoot;

import java.math.BigDecimal;
import java.time.LocalDate;

@AggregateRoot
public class FlightTicket {
    private Id id;
    private String ticketNumber;
    private BigDecimal price;
    private ClassType classType;
    private TicketStatus status;
    private LocalDate purchaseDate;
    private Customer customer;
    private Flight flight;
    private Seat seat;
    private Baggage baggage;

    public FlightTicket(String ticketNumber, BigDecimal price, ClassType classType, TicketStatus status, LocalDate purchaseDate, Customer customer, Flight flight, Seat seat) {
        this.ticketNumber = ticketNumber;
        this.price = price;
        this.classType = classType;
        this.status = status;
        this.purchaseDate = purchaseDate;
        this.customer = customer;
        this.flight = flight;
        this.seat = seat;
        this.id = new Id();
    }

    public FlightTicket(String ticketNumber, BigDecimal price, ClassType classType, TicketStatus status, LocalDate purchaseDate, Customer customer, Flight flight) {
        this.ticketNumber = ticketNumber;
        this.price = price;
        this.classType = classType;
        this.status = status;
        this.purchaseDate = purchaseDate;
        this.customer = customer;
        this.flight = flight;
        this.seat = seat;
        this.id = new Id();
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Baggage getBaggage() {
        return baggage;
    }

    public void setBaggage(Baggage baggage) {
        this.baggage = baggage;
    }
}
