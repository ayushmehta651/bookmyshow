package com.movie_booking.bookmyshow.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CinemahallDTO {
    private int cinemahallId;
    private String cinemahallName;
    private String cinemahallCity;
    private String cinemahallAddress;
}
