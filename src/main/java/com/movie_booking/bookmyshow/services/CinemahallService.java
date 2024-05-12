package com.movie_booking.bookmyshow.services;

import com.movie_booking.bookmyshow.dto.CinemahallDTO;
import com.movie_booking.bookmyshow.dto.CinemahallSeatDTO;
import com.movie_booking.bookmyshow.dto.ResponseDTO;
import com.movie_booking.bookmyshow.entity.CinemaHallSeatsEntity;

import java.util.List;
import java.util.Optional;

public interface CinemahallService {
    ResponseDTO addCinemahall(List<CinemahallDTO> cinemahallDTOList);
    ResponseDTO addCinemahallSeats(CinemahallSeatDTO cinemahallSeatDTO);
    List<CinemaHallSeatsEntity> getCinemahallSeats(Integer id);
}
