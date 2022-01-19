package com.example.cinema.service;

import com.example.cinema.exceptions.NotFoundException;
import com.example.cinema.model.Screening;
import com.example.cinema.repository.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreeningService {
    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }

    public Screening getScreeningById(int screeningId) {
        return screeningRepository.findById(screeningId)
                .orElseThrow(() -> new NotFoundException("Screening"));
    }

    public Screening saveScreening(Screening screening){
        return screeningRepository.save(screening);
    }

    public Screening editScreening(int screeningId, Screening screening) {
        Screening currentScreening = screeningRepository.findById(screeningId)
                .orElseThrow(() -> new NotFoundException("Screening"));

        currentScreening.setDate(screening.getDate());
        currentScreening.setHall(screening.getHall());
        currentScreening.setTicketPrice(screening.getTicketPrice());
        currentScreening.setMovie(screening.getMovie());

        return screeningRepository.save(currentScreening);
    }
}
