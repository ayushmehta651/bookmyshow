package com.movie_booking.bookmyshow.mapper;

import com.movie_booking.bookmyshow.dto.MovieDTO;
import com.movie_booking.bookmyshow.entity.MovieEntity;

public class MovieMapper {
    public static MovieEntity movieDTOtoMovie(MovieDTO movieDTO) {

        return MovieEntity.builder()
                .genre(movieDTO.getGenre())
                .title(movieDTO.getTitle())
                .city(movieDTO.getCity())
                .description(movieDTO.getDescription())
                .duration(movieDTO.getDuration())
                .date(movieDTO.getDate()).build();
    }

    public static MovieDTO movieToMovieDTO(MovieEntity movieEntity) {

        return MovieDTO.builder()
                .id(movieEntity.getId())
                .genre(movieEntity.getGenre())
                .title(movieEntity.getTitle())
                .city(movieEntity.getCity())
                .description(movieEntity.getDescription())
                .duration(movieEntity.getDuration())
                .date(movieEntity.getDate()).build();
    }
}
