package com.example.cinema.service;

import com.example.cinema.enums.TicketType;
import com.example.cinema.model.*;
import com.example.cinema.repository.ReservationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    @DisplayName("Get all reservations successfully")
    void getAll() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservations.add(reservation);
        when(reservationRepository.findAll()).thenReturn(reservations);

        List<Reservation> result = reservationService.getAllReservations();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reservations, result);
    }

    @Test
    @DisplayName("Get reservation by id successfully")
    void getById() {
        Client client = new Client(1, "Ion", "ion@test.com");
        Movie movie = new Movie("Film", "descriere");
        Screening screening = new Screening(LocalDateTime.parse("2022-01-29T16:00:00"), 1, 30, movie);
        Reservation reservation = new Reservation(client, screening);

        when(reservationRepository.findById(reservation.getReservationId())).thenReturn(Optional.of(reservation));

        Reservation result = reservationService.getReservationById(reservation.getReservationId());

        assertNotNull(result);
        assertEquals(reservation.getReservationId(), result.getReservationId());
        assertEquals(reservation.getClient(), result.getClient());
        assertEquals(reservation.getScreening(), result.getScreening());
        assertEquals(reservation.getTickets(), result.getTickets());
        assertEquals(reservation.getTotalPrice(), result.getTotalPrice());
    }

    @Test
    @DisplayName("Reservation created successfully")
    void create() {
        //arrange
        Client client = new Client(1, "Ion", "ion@test.com");
        Movie movie = new Movie("Film", "descriere");
        Screening screening = new Screening(LocalDateTime.parse("2022-01-29T16:00:00"), 1, 30, movie);
        Reservation reservation = new Reservation(client, screening);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        //act
        Reservation result = reservationService.saveReservation(reservation);

        //assert
        assertNotNull(result);
        assertEquals(reservation.getReservationId(), result.getReservationId());
        assertEquals(reservation.getClient(), result.getClient());
        assertEquals(reservation.getScreening(), result.getScreening());
        assertEquals(reservation.getTickets(), result.getTickets());
        assertEquals(reservation.getTotalPrice(), result.getTotalPrice());
    }

    @Test
    @DisplayName("Add ticket to reservation successfully")
    void addTicket() {
        Client client = new Client(1, "Ion", "ion@test.com");
        Movie movie = new Movie("Film", "descriere");
        Screening screening = new Screening(LocalDateTime.parse("2022-01-29T16:00:00"), 1, 30, movie);
        Reservation reservation = new Reservation(client, screening);
        Ticket ticket = new Ticket(reservation, TicketType.ADULT, 10);

        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation result = reservationService.addTicketToReservation(ticket);

        assertNotNull(result);
        assertEquals(reservation.getReservationId(), result.getReservationId());
        assertEquals(reservation.getClient(), result.getClient());
        assertEquals(reservation.getScreening(), result.getScreening());
        assertEquals(reservation.getTickets(), result.getTickets());
        assertEquals(reservation.getTotalPrice(), result.getTotalPrice());
    }

    @Test
    @DisplayName("Delete reservation by id successfully")
    void delete() {
        String result = reservationService.deleteReservationById(0);
        verify(reservationRepository).deleteById(0);
        assertEquals("Reservation deleted successfully", result);
    }
}
