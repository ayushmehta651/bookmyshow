package com.movie_booking.bookmyshow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "booking"
)
@Builder
public class BookingEntity {
    @Id
    @SequenceGenerator(
            sequenceName = "bookingIdSequence",
            allocationSize = 1,
            name = "bookingIdSequence"
    )
    @GeneratedValue(
            generator = "bookingIdSequence",
            strategy = GenerationType.SEQUENCE
    )
    private int bookingId;

    private float bookingPrice;

    @CreationTimestamp
    @Column(name = "booked_at", nullable = false)
    private Date bookingDate;

    @Column(name = "alloted_seats", nullable = false)
    private String allottedSeats;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private ShowEntity show;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private UserEntity user;

    @OneToMany(mappedBy = "seats", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShowSeatsEntity> seats;
}
