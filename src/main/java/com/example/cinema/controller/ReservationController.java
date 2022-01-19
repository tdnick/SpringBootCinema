package com.example.cinema.controller;

import com.example.cinema.dto.ReservationDto;
import com.example.cinema.mapper.ReservationMapper;
import com.example.cinema.model.Reservation;
import com.example.cinema.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.ok().body(reservationService.getAllReservations());
    }

    @GetMapping
    public ResponseEntity<Reservation> getReservationById(
            @RequestParam int reservationId
    ) {
        return ResponseEntity.ok().body(reservationService.getReservationById(reservationId));
    }

    @PostMapping("/new")
    public ResponseEntity<Reservation> createReservation(
            @RequestBody ReservationDto reservationDto
    ) {
        Reservation reservation = reservationService.saveReservation(reservationMapper.dtoToEntity(reservationDto));
        return ResponseEntity.created(URI.create("/reservation" + reservation.getReservationId()))
                .body(reservation);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> deleteReservationById(
            @PathVariable int reservationId
    ) {
        return ResponseEntity.ok().body(reservationService.deleteReservationById(reservationId));
    }
}
