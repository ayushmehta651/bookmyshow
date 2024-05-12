package com.movie_booking.bookmyshow.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Builder
@Data
public class MovieDTO {
    private int id;
    private String title;
    private String description;
    private String duration;
    private List<String> city;
    private Date date;
    private String genre;

}
