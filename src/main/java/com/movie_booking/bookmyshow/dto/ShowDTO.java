package com.movie_booking.bookmyshow.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
public class ShowDTO {
    private int showId;
    private Date showDate;
    private Time startTime;
    private Time endTime;
    private Integer cinemahallId;
    private Integer movieId;
}
