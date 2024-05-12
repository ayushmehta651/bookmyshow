package com.movie_booking.bookmyshow.services;

import com.movie_booking.bookmyshow.dto.BookingReponseDTO;
import com.movie_booking.bookmyshow.dto.BookingRequestDTO;

public interface BookingService {
    BookingReponseDTO addBooking(BookingRequestDTO bookingDTO);
}
