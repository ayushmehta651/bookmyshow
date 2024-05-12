package com.movie_booking.bookmyshow.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CinemahallSeatDTO {
    private int cinemahallId;
    private int noOfClassicSeat;
    private int noOfPremiumSeat;
    private int noOfReclinerSeat;
}
