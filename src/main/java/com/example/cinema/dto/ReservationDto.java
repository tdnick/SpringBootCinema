package com.example.cinema.dto;

import javax.validation.constraints.NotNull;

public class ReservationDto {
    @NotNull
    private int clientId;

    @NotNull
    private int screeningId;

    public ReservationDto() {
    }

    public ReservationDto(int clientId, int screeningId) {
        this.clientId = clientId;
        this.screeningId = screeningId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(int screeningId) {
        this.screeningId = screeningId;
    }
}
