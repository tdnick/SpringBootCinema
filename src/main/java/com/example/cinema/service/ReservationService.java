package com.example.cinema.service;

import com.example.cinema.exceptions.NotFoundException;
import com.example.cinema.model.Reservation;
import com.example.cinema.model.Ticket;
import com.example.cinema.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(int reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation"));
    }

    public Reservation saveReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public Reservation addTicketToReservation(Ticket ticket){
        Reservation reservation = ticket.getReservation();
        reservation.addTicket(ticket);
        reservation.setTotalPrice(reservation.getTotalPrice() + ticket.getPrice());
        return reservationRepository.save(reservation);
    }

    public String deleteReservationById(int reservationId) {
        reservationRepository.deleteById(reservationId);
        return "Reservation deleted successfully";
    }
}
