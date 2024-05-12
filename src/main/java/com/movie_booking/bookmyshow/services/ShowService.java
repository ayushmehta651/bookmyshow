package com.movie_booking.bookmyshow.services;

import com.movie_booking.bookmyshow.dto.ResponseDTO;
import com.movie_booking.bookmyshow.dto.ShowDTO;
import com.movie_booking.bookmyshow.entity.MovieEntity;
import com.movie_booking.bookmyshow.entity.ShowEntity;
import com.movie_booking.bookmyshow.entity.ShowSeatsEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ShowService {
    ResponseDTO addShow(List<ShowDTO> showDTOList);
    List<ShowDTO> getAllShowsOfMovie(Integer movieId);
    ResponseDTO addSeatsToParticularShow(Integer showId);
    List<ShowSeatsEntity> getShowSeats(Integer showId);
}
