package com.example.cinema.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ScreeningDto {
    @NotNull
    private LocalDateTime date;

    @Min(1)
    @Max(5)
    @NotNull
    private int hall;

    @NotNull
    @Min(0)
    private double ticketPrice;

    @NotNull
    private int movieId;

    public ScreeningDto() {
    }

    public ScreeningDto(LocalDateTime date, int hall, int movieId) {
        this.date = date;
        this.hall = hall;
        this.movieId = movieId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getHall() {
        return hall;
    }

    public void setHall(int hall) {
        this.hall = hall;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
