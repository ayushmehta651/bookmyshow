package com.movie_booking.bookmyshow.controller;

import com.movie_booking.bookmyshow.dto.MovieDTO;
import com.movie_booking.bookmyshow.dto.ResponseDTO;
import com.movie_booking.bookmyshow.entity.MovieEntity;
import com.movie_booking.bookmyshow.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/movie/v1")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addMovies(
            @RequestBody List<MovieDTO> movieDTO
    ) {
        try{
            ResponseDTO res = movieService.addMovies(movieDTO);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> getAllMovies(
            @RequestParam(name = "movie_name", required = false) String movie_name,
            @RequestParam(name = "movie_city", required = false) List<String> movie_city,
            @RequestParam(name = "movie_date", required = false) Date movie_date
    ) {
        try {
            List<MovieDTO> movie = movieService.getAllMovies(movie_name, movie_city, movie_date);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
