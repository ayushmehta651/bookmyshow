package com.movie_booking.bookmyshow.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class BookingReponseDTO {
    private int bookingId;

    private float bookingPrice;

    private Date bookingDate;

    private String allottedSeats;
}
