package com.example.cinema.service;

import com.example.cinema.model.Movie;
import com.example.cinema.model.Screening;
import com.example.cinema.repository.ScreeningRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScreeningServiceTest {
    @Mock
    private ScreeningRepository screeningRepository;

    @InjectMocks
    private ScreeningService screeningService;

    @Test
    @DisplayName("Get all screenings successfully")
    void getAll() {
        List<Screening> screenings = new ArrayList<>();
        Screening screening = new Screening();
        screenings.add(screening);
        when(screeningRepository.findAll()).thenReturn(screenings);

        List<Screening> result = screeningService.getAllScreenings();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(screenings, result);
    }

    @Test
    @DisplayName("Get screening by id successfully")
    void getById() {
        Movie movie = new Movie("Film", "descriere");
        Screening screening = new Screening(LocalDateTime.parse("2022-01-29T16:00:00"), 1, 30, movie);

        when(screeningRepository.findById(screening.getScreeningId())).thenReturn(Optional.of(screening));

        Screening result = screeningService.getScreeningById(screening.getScreeningId());

        assertNotNull(result);
        assertEquals(screening.getScreeningId(), result.getScreeningId());
        assertEquals(screening.getMovie(), result.getMovie());
        assertEquals(screening.getDate(), result.getDate());
        assertEquals(screening.getHall(), result.getHall());
        assertEquals(screening.getTicketPrice(), result.getTicketPrice());
    }

    @Test
    @DisplayName("Screening created successfully")
    void create() {
        //arrange
        Movie movie = new Movie("Film", "descriere");
        Screening screening = new Screening(LocalDateTime.parse("2022-01-29T16:00:00"), 1, 30, movie);
        when(screeningRepository.save(screening)).thenReturn(screening);

        //act
        Screening result = screeningService.saveScreening(screening);

        //assert
        assertNotNull(result);
        assertEquals(screening.getScreeningId(), result.getScreeningId());
        assertEquals(screening.getMovie(), result.getMovie());
        assertEquals(screening.getDate(), result.getDate());
        assertEquals(screening.getHall(), result.getHall());
        assertEquals(screening.getTicketPrice(), result.getTicketPrice());
    }

    @Test
    @DisplayName("Screening edited successfully")
    void updateSuccess() {
        Movie oldMovie = new Movie(1, "Film", "descriere");
        Movie newMovie = new Movie(2, "film2", "desc2");

        Screening oldScreening = new Screening(1, LocalDateTime.parse("2022-01-29T16:00:00"), 1, 30, oldMovie);
        Screening newScreening = new Screening(1, LocalDateTime.parse("2022-01-30T18:25:00"), 2, 35, newMovie);

        when(screeningRepository.findById(oldScreening.getScreeningId())).thenReturn(Optional.of(oldScreening));
        when(screeningRepository.save(oldScreening)).thenReturn(newScreening);

        Screening result = screeningService.editScreening(1, newScreening);

        assertNotNull(result);
        assertEquals(newScreening.getScreeningId(), result.getScreeningId());
        assertEquals(newScreening.getMovie(), result.getMovie());
        assertEquals(newScreening.getDate(), result.getDate());
        assertEquals(newScreening.getHall(), result.getHall());
        assertEquals(newScreening.getTicketPrice(), result.getTicketPrice());
    }
}
