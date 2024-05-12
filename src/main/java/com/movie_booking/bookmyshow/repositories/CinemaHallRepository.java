package com.movie_booking.bookmyshow.repositories;

import com.movie_booking.bookmyshow.entity.CinemaHallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaHallRepository extends JpaRepository<CinemaHallEntity, Integer> {
}
