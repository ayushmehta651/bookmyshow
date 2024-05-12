package com.movie_booking.bookmyshow.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public class LoginResponseDTO {
        @Getter
        private String token;
        private long expiresIn;
}
