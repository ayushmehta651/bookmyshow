package com.movie_booking.bookmyshow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movie_booking.bookmyshow.enums.SeatTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.Temporal;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(
        name = "show_seat"
)
public class ShowSeatsEntity {
    @Id
    @SequenceGenerator(
            name = "show_seat_sequence",
            allocationSize = 1,
            sequenceName = "show_seat_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "show_seat_sequence"
    )
    private int seatId;

    @Enumerated(value = EnumType.STRING)
    private SeatTypeEnum seatType;

    private String seatNumber;

    private double seatPrice;

    private Boolean isAvailable;

    @CreationTimestamp
    private Date booking_date;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private ShowEntity show;

    @ManyToOne
    @JoinColumn
    private BookingEntity seats;
}
