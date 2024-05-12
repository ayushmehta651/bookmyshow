package com.movie_booking.bookmyshow.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Integer id;

    private String name;

    private String email;

    private String password;

    private String address;

    private String age;

    private String mobile_no;
}
