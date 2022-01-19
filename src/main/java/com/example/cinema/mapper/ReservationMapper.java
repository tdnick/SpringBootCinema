package com.example.cinema.mapper;

import com.example.cinema.dto.ReservationDto;
import com.example.cinema.exceptions.NotFoundException;
import com.example.cinema.model.Client;
import com.example.cinema.model.Reservation;
import com.example.cinema.model.Screening;
import com.example.cinema.repository.ClientRepository;
import com.example.cinema.repository.ScreeningRepository;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    private final ClientRepository clientRepository;
    private final ScreeningRepository screeningRepository;

    public ReservationMapper(ClientRepository clientRepository, ScreeningRepository screeningRepository) {
        this.clientRepository = clientRepository;
        this.screeningRepository = screeningRepository;
    }

    public Reservation dtoToEntity(ReservationDto request){
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new NotFoundException("Client"));
        Screening screening = screeningRepository.findById(request.getScreeningId())
                .orElseThrow(() -> new NotFoundException("Screening"));
        return new Reservation(client, screening);
    }
}
