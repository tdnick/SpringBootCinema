package com.example.cinema.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int screeningId;

    @NotNull
    private LocalDateTime date;

    @Min(1)
    @Max(5)
    @NotNull
    private int hall;

    @NotNull
    @Min(0)
    private double ticketPrice;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @NotNull
    private Movie movie;

    public Screening() {
    }

    public Screening(LocalDateTime date, int hall, double ticketPrice, Movie movie) {
        this.date = date;
        this.hall = hall;
        this.movie = movie;
        this.ticketPrice = ticketPrice;
    }

    public Screening(int screeningId, LocalDateTime date, int hall, double ticketPrice, Movie movie) {
        this.screeningId = screeningId;
        this.date = date;
        this.hall = hall;
        this.ticketPrice = ticketPrice;
        this.movie = movie;
    }

    public Screening(LocalDateTime date, int hall, double ticketPrice){
        this.date = date;
        this.hall = hall;
        this.ticketPrice = ticketPrice;
    }

    public int getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(int screeningId) {
        this.screeningId = screeningId;
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
