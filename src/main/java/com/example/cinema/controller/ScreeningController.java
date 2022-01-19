package com.example.cinema.controller;

import com.example.cinema.dto.ScreeningDto;
import com.example.cinema.mapper.ScreeningMapper;
import com.example.cinema.model.Screening;
import com.example.cinema.service.ScreeningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/screening")
public class ScreeningController {
    private final ScreeningService screeningService;
    private final ScreeningMapper screeningMapper;

    public ScreeningController(ScreeningService screeningService, ScreeningMapper screeningMapper) {
        this.screeningService = screeningService;
        this.screeningMapper = screeningMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Screening>> getScreenings() {
        return ResponseEntity.ok().body(screeningService.getAllScreenings());
    }

    @GetMapping
    public ResponseEntity<Screening> getScreeningById(
            @RequestParam int screeningId
    ) {
        return ResponseEntity.ok().body(screeningService.getScreeningById(screeningId));
    }

    @PostMapping("/new")
    public ResponseEntity<Screening> createScreening(
            @RequestBody ScreeningDto screeningDto
    ) {
        Screening screening = screeningService.saveScreening(screeningMapper.dtoToEntity(screeningDto));
        return ResponseEntity.created(URI.create("/screening" + screening.getScreeningId()))
                .body(screening);
    }

    @PutMapping("/{screeningId}")
    public ResponseEntity<Screening> updateScreening(
            @PathVariable int screeningId,
            @RequestBody ScreeningDto screeningDto
    ) {
        Screening screening = screeningService.editScreening(screeningId, screeningMapper.dtoToEntity(screeningDto));
        return ResponseEntity.ok().body(screening);
    }
}
