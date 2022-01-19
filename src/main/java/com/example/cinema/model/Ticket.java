package com.example.cinema.model;

import com.example.cinema.enums.TicketType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    @NotNull
    @JsonIgnore
    private Reservation reservation;

    @NotNull
    private TicketType ticketType;

    @Min(0)
    private double price;

    public Ticket() {
    }

    public Ticket(Reservation reservation, TicketType ticketType) {
        this.reservation = reservation;
        this.ticketType = ticketType;
    }

    public Ticket(Reservation reservation, TicketType ticketType, double price) {
        this.reservation = reservation;
        this.ticketType = ticketType;
        this.price = price;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
