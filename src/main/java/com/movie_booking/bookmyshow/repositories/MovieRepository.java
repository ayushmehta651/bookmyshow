package com.movie_booking.bookmyshow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.movie_booking.bookmyshow.entity.MovieEntity;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, String> {

    List<MovieEntity> findAllByTitleContaining(String movie_name);
    List<MovieEntity> findAllByCityIn(List<String> movie_city);
    MovieEntity findByTitle(String movie_title);
    List<MovieEntity> findAllByDate(Date movie_date);
    Optional<MovieEntity> findById(Integer movie_id);
}
