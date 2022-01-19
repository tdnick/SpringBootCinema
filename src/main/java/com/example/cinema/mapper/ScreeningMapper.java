package com.example.cinema.mapper;

import com.example.cinema.dto.ScreeningDto;
import com.example.cinema.exceptions.NotFoundException;
import com.example.cinema.model.Movie;
import com.example.cinema.model.Screening;
import com.example.cinema.repository.MovieRepository;
import org.springframework.stereotype.Component;

@Component
public class ScreeningMapper {
    private MovieRepository movieRepository;

    public ScreeningMapper(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public Screening dtoToEntity(ScreeningDto request){
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie"));
        return new Screening(request.getDate(), request.getHall(), request.getTicketPrice(), movie);
    }
}
