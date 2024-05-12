package com.movie_booking.bookmyshow.repositories;

import com.movie_booking.bookmyshow.dto.ShowDTO;
import com.movie_booking.bookmyshow.entity.MovieEntity;
import com.movie_booking.bookmyshow.entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {
    @Query(value = "select * from shows where movie_movie_id = :movieId", nativeQuery = true)
    List<ShowEntity> getAllShowsOfMovie(Integer movieId);
}
