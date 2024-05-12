package com.movie_booking.bookmyshow.repositories;

import com.movie_booking.bookmyshow.entity.ShowSeatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeatsEntity, Integer> {
    @Query(value = "select * from show_seat where show_show_id = :showId", nativeQuery = true)
    List<ShowSeatsEntity> findAllByShowId(Integer showId);

    Optional<ShowSeatsEntity> findBySeatId(Integer seatId);
}
