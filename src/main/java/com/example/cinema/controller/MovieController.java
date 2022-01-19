package com.example.cinema.controller;

import com.example.cinema.model.Movie;
import com.example.cinema.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok().body(movieService.getAllMovies());
    }

    @PostMapping("/new")
    public ResponseEntity<Movie> createMovie(
            @RequestBody Movie movie
    ) {
        return ResponseEntity.created(URI.create("/movie" + movie.getMovieId()))
                .body(movieService.saveMovie(movie));
    }
}
