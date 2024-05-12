package com.movie_booking.bookmyshow.mapper;

import com.movie_booking.bookmyshow.dto.ShowDTO;
import com.movie_booking.bookmyshow.entity.ShowEntity;

public class ShowMapper {
    public static ShowEntity showDTOtoShow(ShowDTO showDTO) {
        return ShowEntity.builder()
                .showDate(showDTO.getShowDate())
                .endTime(showDTO.getEndTime())
                .startTime(showDTO.getStartTime())
                .build();
    }

    public static ShowDTO showToShowDTO(ShowEntity showEntity) {
        return ShowDTO.builder()
                .showDate(showEntity.getShowDate())
                .endTime(showEntity.getEndTime())
                .startTime(showEntity.getStartTime())
                .movieId(showEntity.getMovie().getId())
                .cinemahallId(showEntity.getCinemahall().getCinemahallId())
                .showId(showEntity.getShowId())
                .build();
    }
}
