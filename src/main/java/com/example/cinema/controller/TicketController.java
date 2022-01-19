package com.example.cinema.controller;

import com.example.cinema.dto.TicketDto;
import com.example.cinema.mapper.TicketMapper;
import com.example.cinema.model.Ticket;
import com.example.cinema.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    public TicketController(TicketService ticketService, TicketMapper ticketMapper){
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getTickets() {
        return ResponseEntity.ok().body(ticketService.getAllTickets());
    }

    @PostMapping("/new")
    public ResponseEntity<Ticket> createTicket(
            @RequestBody TicketDto ticketDto
    ) {
        Ticket ticket = ticketService.saveTicket(ticketMapper.dtoToEntity(ticketDto));
        return ResponseEntity.created(URI.create("/ticket" + ticket.getTicketId()))
                .body(ticket);
    }
}
