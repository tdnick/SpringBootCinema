package com.example.cinema.dto;

import com.example.cinema.enums.TicketType;

import javax.validation.constraints.NotNull;

public class TicketDto {
    @NotNull
    private int reservationId;

    @NotNull
    private TicketType ticketType;

    public TicketDto() {
    }

    public TicketDto(int reservationId, TicketType ticketType) {
        this.reservationId = reservationId;
        this.ticketType = ticketType;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }
}
