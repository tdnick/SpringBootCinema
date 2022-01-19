package com.example.cinema.mapper;

import com.example.cinema.dto.TicketDto;
import com.example.cinema.exceptions.NotFoundException;
import com.example.cinema.model.Reservation;
import com.example.cinema.model.Ticket;
import com.example.cinema.repository.ReservationRepository;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    private ReservationRepository reservationRepository;

    public TicketMapper(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Ticket dtoToEntity(TicketDto request){
        Reservation reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new NotFoundException("Reservation"));
        return new Ticket(reservation, request.getTicketType());
    }
}
