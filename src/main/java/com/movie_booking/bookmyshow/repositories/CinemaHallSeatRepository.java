package com.movie_booking.bookmyshow.repositories;

import com.movie_booking.bookmyshow.entity.CinemaHallSeatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaHallSeatRepository extends JpaRepository<CinemaHallSeatsEntity, Integer> {
    @Query(value = "select * from cinemahall_seats where cinemahall_cinemahall_id = :cinemahallId", nativeQuery = true)
    List<CinemaHallSeatsEntity> findAllByCinemahall(int cinemahallId);
}
