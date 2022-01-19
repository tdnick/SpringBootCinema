package com.example.cinema;

import com.example.cinema.enums.TicketType;
import com.example.cinema.model.*;
import com.example.cinema.repository.*;
import com.example.cinema.service.TicketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final MembershipRepository membershipRepository;
    private final MovieRepository movieRepository;
    private final ReservationRepository reservationRepository;
    private final ScreeningRepository screeningRepository;
    private final TicketRepository ticketRepository;
    private final TicketService ticketService;

    public CinemaApplication(
            ClientRepository clientRepository,
            MembershipRepository membershipRepository,
            MovieRepository movieRepository,
            ReservationRepository reservationRepository,
            ScreeningRepository screeningRepository,
            TicketRepository ticketRepository,
            TicketService ticketService)
    {
        this.clientRepository = clientRepository;
        this.membershipRepository = membershipRepository;
        this.movieRepository = movieRepository;
        this.reservationRepository = reservationRepository;
        this.screeningRepository = screeningRepository;
        this.ticketRepository = ticketRepository;
        this.ticketService = ticketService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Generate clients
        Client client1 = new Client("Ion", "ion@test.com");
        Client client2 = new Client("Ioana", "ioana@test.com");

        // Second client will be assigned a membership
        Membership membership1 = new Membership();
        membershipRepository.save(membership1);

        client2.setMembership(membership1);

        clientRepository.save(client1);
        clientRepository.save(client2);

        // Generate movie
        Movie movie1 = new Movie("Filmul 1", "Cel mai tare film");
        movieRepository.save(movie1);

        // Generate screening for movie
        Screening screening1 = new Screening(LocalDateTime.parse("2022-01-29T16:00:00"), 1, 30, movie1);
        screeningRepository.save(screening1);

        // Generate ticket reservations
        Reservation reservation1 = new Reservation(client1, screening1);
        Reservation reservation2 = new Reservation(client2, screening1);

        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);

        // Generate tickets
        Ticket ticket1 = new Ticket(reservation1, TicketType.ADULT);
        Ticket ticket2 = new Ticket(reservation1, TicketType.CHILD);

        Ticket ticket3 = new Ticket(reservation2, TicketType.STUDENT);

        // Automatically calculate price and add to reservation
        ticketService.saveTicket(ticket1);
        ticketService.saveTicket(ticket2);
        ticketService.saveTicket(ticket3);

    }

}
