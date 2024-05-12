package com.movie_booking.bookmyshow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "shows")
public class ShowEntity {
    @Id
    @SequenceGenerator(
            name = "show_sequence",
            sequenceName = "show_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "show_sequence"
    )
    @Column(
            name = "show_id",
            nullable = false
    )
    private int showId;
    @Column(
            name = "show_date",
            nullable = false
    )
    private Date showDate;
    @Column(
            name = "start_time",
            nullable = false
    )
    private Time startTime;
    @Column(
            name = "end_time",
            nullable = false
    )
    private Time endTime;

    @ManyToOne
    @JoinColumn
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn
    private CinemaHallEntity cinemahall;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "show")
    private List<ShowSeatsEntity> showSeats = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "show")
    private List<BookingEntity> showBookings;
}
