package com.movie_booking.bookmyshow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "movie",
        uniqueConstraints = @UniqueConstraint(
        name = "movie_title_unique",
        columnNames = "movie_title"
))
public class MovieEntity {
        @Id
        @SequenceGenerator(
                name = "movie_sequence",
                sequenceName = "movie_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "movie_sequence"
        )
        @Column(
                name = "movie_id",
                nullable = false
        )
        private int id;
        @Column(
                name = "movie_name",
                nullable = false
        )
        private String title;
        @Column(
                name = "movie_description",
                nullable = false
        )
        private String description;
        @Column(
                name = "movie_duration",
                nullable = false
        )
        private String duration;
        @Column(
                name = "movie_city",
                nullable = false
        )
        private List<String> city = new ArrayList<>();
        @Column(
                name = "movie_genre",
                nullable = false
        )
        private String genre;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
        @Column(name = "movie_date", nullable = false)
        private Date date;

        @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
        private List<ShowEntity> shows = new ArrayList<>();
}
