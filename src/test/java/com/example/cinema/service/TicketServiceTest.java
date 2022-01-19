package com.example.cinema.service;

import com.example.cinema.enums.TicketType;
import com.example.cinema.model.*;
import com.example.cinema.repository.TicketRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    @DisplayName("Get all tickets successfully")
    void getAll() {
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket = new Ticket();
        tickets.add(ticket);
        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.getAllTickets();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(tickets, result);
    }

    @Test
    @DisplayName("Save adult ticket successfully")
    void update() {
        Client client = new Client(1, "Ion", "ion@test.com");
        Movie movie = new Movie("Film", "descriere");
        Screening screening = new Screening(LocalDateTime.parse("2022-01-29T16:00:00"), 1, 30, movie);
        Reservation reservation = new Reservation(client, screening);
        Ticket ticket = new Ticket(reservation, TicketType.ADULT);

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.saveTicket(ticket);

        assertNotNull(result);
        assertEquals(ticket.getTicketType(), result.getTicketType());
        assertEquals(ticket.getTicketId(), result.getTicketId());
        assertEquals(screening.getTicketPrice(), result.getPrice());
    }

    @Test
    @DisplayName("Save child ticket successfully")
    void updateChild() {
        Client client = new Client(1, "Ion", "ion@test.com");
        Movie movie = new Movie("Film", "descriere");
        Screening screening = new Screening(LocalDateTime.parse("2022-01-29T16:00:00"), 1, 30, movie);
        Reservation reservation = new Reservation(client, screening);
        Ticket ticket = new Ticket(reservation, TicketType.CHILD);

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.saveTicket(ticket);

        assertNotNull(result);
        assertEquals(ticket.getTicketType(), result.getTicketType());
        assertEquals(ticket.getTicketId(), result.getTicketId());
        assertEquals(0.5 * screening.getTicketPrice(), result.getPrice());
    }

    @Test
    @DisplayName("Save student ticket successfully")
    void updateStudent() {
        Client client = new Client(1, "Ion", "ion@test.com");
        Movie movie = new Movie("Film", "descriere");
        Screening screening = new Screening(LocalDateTime.parse("2022-01-29T16:00:00"), 1, 30, movie);
        Reservation reservation = new Reservation(client, screening);
        Ticket ticket = new Ticket(reservation, TicketType.STUDENT);

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.saveTicket(ticket);

        assertNotNull(result);
        assertEquals(ticket.getTicketType(), result.getTicketType());
        assertEquals(ticket.getTicketId(), result.getTicketId());
        assertEquals(0.75 * screening.getTicketPrice(), result.getPrice());
    }

    @Test
    @DisplayName("Save adult ticket with membership successfully")
    void updateWithMembership() {
        Client client = new Client(1, "Ion", "ion@test.com");
        Membership membership = new Membership();
        client.setMembership(membership);
        Movie movie = new Movie("Film", "descriere");
        Screening screening = new Screening(LocalDateTime.parse("2022-01-29T16:00:00"), 1, 30, movie);
        Reservation reservation = new Reservation(client, screening);
        Ticket ticket = new Ticket(reservation, TicketType.ADULT);

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.saveTicket(ticket);

        assertNotNull(result);
        assertEquals(ticket.getTicketType(), result.getTicketType());
        assertEquals(ticket.getTicketId(), result.getTicketId());
        assertEquals(0.8 * screening.getTicketPrice(), result.getPrice());
    }

}
