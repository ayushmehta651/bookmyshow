package com.movie_booking.bookmyshow.services;

import com.movie_booking.bookmyshow.dto.MovieDTO;
import com.movie_booking.bookmyshow.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.List;

public interface MovieService {
    List<MovieDTO> getAllMovies(String movie_name, List<String> movie_city, Date movie_date);
    ResponseDTO addMovies(List<MovieDTO> movieDTO);
}
