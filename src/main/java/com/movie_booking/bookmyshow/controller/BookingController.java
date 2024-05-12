package com.movie_booking.bookmyshow.controller;

import com.movie_booking.bookmyshow.dto.BookingReponseDTO;
import com.movie_booking.bookmyshow.dto.BookingRequestDTO;
import com.movie_booking.bookmyshow.dto.ResponseDTO;
import com.movie_booking.bookmyshow.services.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/booking/v1")
public class BookingController {

    @Autowired
    BookingService bookingService;
    @PostMapping(value = "/add")
    ResponseEntity<?> addBooking(@Valid @RequestBody BookingRequestDTO bookingDTO) {
        try {
            BookingReponseDTO responseDTO = bookingService.addBooking(bookingDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
