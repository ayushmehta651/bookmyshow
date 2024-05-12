package com.movie_booking.bookmyshow.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class BookingRequestDTO {
    private Set<Integer> seatIds;
    private int showId;
    private String email;
}
