package com.example.cinema.service;

import com.example.cinema.enums.MembershipStatus;
import com.example.cinema.model.*;
import com.example.cinema.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket saveTicket(Ticket ticket){
        ticket.setPrice(calculateTicketPrice(ticket));
        Ticket newTicket = ticketRepository.save(ticket);
        return newTicket;
    }

    private double calculateTicketPrice(Ticket ticket){
        Reservation reservation = ticket.getReservation();
        Screening screening = reservation.getScreening();
        Client client = reservation.getClient();
        Membership membership = client.getMembership();

        double price = screening.getTicketPrice();

        switch (ticket.getTicketType()){
            case CHILD:
                price = 0.5 * price;
                break;
            case STUDENT:
                price = 0.75 * price;
                break;
        }

        return (membership != null && membership.getMembershipStatus().equals(MembershipStatus.ACTIVE))
                ? 0.8 * price
                : price;
    }
}
