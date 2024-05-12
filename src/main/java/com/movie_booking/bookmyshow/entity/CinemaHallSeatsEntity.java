package com.movie_booking.bookmyshow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movie_booking.bookmyshow.enums.SeatTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "cinemahall_seats")
@AllArgsConstructor
@NoArgsConstructor
public class CinemaHallSeatsEntity {
    @Id
    @SequenceGenerator(
            name = "cinemahall_seatId",
            allocationSize = 1,
            sequenceName = "cinemahall_seatId"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cinemahall_seatId"
    )
    private int cinemahallSeatId;

    @Enumerated(value = EnumType.STRING)
    private SeatTypeEnum cinemaHallSeatType;

    private String cinemaHallSeatNumber;

    private double cinemahallSeatPrice;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    CinemaHallEntity cinemahall;
}
