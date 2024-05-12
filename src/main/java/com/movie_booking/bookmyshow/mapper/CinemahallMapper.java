package com.movie_booking.bookmyshow.mapper;

import com.movie_booking.bookmyshow.dto.CinemahallDTO;
import com.movie_booking.bookmyshow.entity.CinemaHallEntity;

public class CinemahallMapper {
    public static CinemahallDTO CinemahallToCinemahallDTO(CinemaHallEntity cinemaHallEntity) {
        return CinemahallDTO.builder()
                .cinemahallAddress(cinemaHallEntity.getCinemahallAddress())
                .cinemahallCity(cinemaHallEntity.getCinemahallCity())
                .cinemahallName(cinemaHallEntity.getCinemahallName())
                .build();
    }
    public static CinemaHallEntity CinemahallDTOToCinemahall(CinemahallDTO cinemahallDTO) {
        return CinemaHallEntity.builder()
                .cinemahallAddress(cinemahallDTO.getCinemahallAddress())
                .cinemahallCity(cinemahallDTO.getCinemahallCity())
                .cinemahallName(cinemahallDTO.getCinemahallName())
                .build();
    }
}
