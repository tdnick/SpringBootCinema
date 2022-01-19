package com.example.cinema.service;

import com.example.cinema.model.Client;
import com.example.cinema.model.Movie;
import com.example.cinema.repository.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    @DisplayName("Get all movies successfully")
    void getAll() {
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movies.add(movie);
        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = movieService.getAllMovies();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(movies, result);
    }

    @Test
    @DisplayName("Movie created successfully")
    void create() {
        //arrange
        Movie movie = new Movie("Film1", "descriere");
        when(movieRepository.save(movie)).thenReturn(movie);

        //act
        Movie result = movieService.saveMovie(movie);

        //assert
        assertNotNull(result);
        assertEquals(movie.getMovieId(), result.getMovieId());
        assertEquals(movie.getTitle(), result.getTitle());
        assertEquals(movie.getDescription(), result.getDescription());
    }

}
