package com.movie_booking.bookmyshow.mapper;

import com.movie_booking.bookmyshow.dto.BookingReponseDTO;
import com.movie_booking.bookmyshow.dto.BookingRequestDTO;
import com.movie_booking.bookmyshow.entity.BookingEntity;

public class BookingMapper {
    public static BookingReponseDTO BookingToBookingDTO(BookingEntity booking) {
        return BookingReponseDTO.builder()
                .bookingId(booking.getBookingId())
                .allottedSeats(booking.getAllottedSeats())
                .bookingPrice(booking.getBookingPrice())
                .build();
    }
}
