package com.movie_booking.bookmyshow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cinemahall")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CinemaHallEntity {
    @Id
    @SequenceGenerator(
            name = "cinemahall_sequence",
            allocationSize = 1,
            sequenceName = "cinemahall_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cinemahall_sequence"
    )
    private int cinemahallId;
    private String cinemahallName;
    private String cinemahallCity;

    @Column(unique = true)
    private String cinemahallAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinemahall")
    List<ShowEntity> showEntityList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinemahall")
    List<CinemaHallSeatsEntity> cinemaHallSeats = new ArrayList<>();
}
