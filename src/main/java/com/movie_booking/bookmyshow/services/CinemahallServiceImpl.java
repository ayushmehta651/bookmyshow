package com.movie_booking.bookmyshow.services;

import com.movie_booking.bookmyshow.custom_exceptions.BadRequestException;
import com.movie_booking.bookmyshow.dto.CinemahallDTO;
import com.movie_booking.bookmyshow.dto.CinemahallSeatDTO;
import com.movie_booking.bookmyshow.dto.ResponseDTO;
import com.movie_booking.bookmyshow.entity.CinemaHallEntity;
import com.movie_booking.bookmyshow.entity.CinemaHallSeatsEntity;
import com.movie_booking.bookmyshow.enums.SeatTypeEnum;
import com.movie_booking.bookmyshow.mapper.CinemahallMapper;
import com.movie_booking.bookmyshow.repositories.CinemaHallRepository;
import com.movie_booking.bookmyshow.repositories.CinemaHallSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemahallServiceImpl implements CinemahallService{
    @Autowired
    CinemaHallRepository cinemaHallRepository;
    @Autowired
    CinemaHallSeatRepository cinemaHallSeatRepository;
    @Override
    public ResponseDTO addCinemahall(List<CinemahallDTO> cinemahallDTOList) {

        cinemahallDTOList.forEach(cinemahallDTO -> {
            CinemaHallEntity cinemaHallEntity = CinemahallMapper.CinemahallDTOToCinemahall(cinemahallDTO);
            cinemaHallRepository.save(cinemaHallEntity);
        });

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("success");
        responseDTO.setMessage("Cinemahall Details added Successfully");

        return responseDTO;
    }

    @Override
    public ResponseDTO addCinemahallSeats(CinemahallSeatDTO cinemahallSeatDTO) {
        Optional<CinemaHallEntity> cinemaHall = cinemaHallRepository.findById(cinemahallSeatDTO.getCinemahallId());
        if(cinemaHall.isEmpty()) {
            throw new BadRequestException("No Cinemahall present with id: " + cinemahallSeatDTO.getCinemahallId());
        }
        CinemaHallEntity cinemaHallEntity = cinemaHall.get();
        List<CinemaHallSeatsEntity> cinemaHallSeatsEntityList = cinemaHallEntity.getCinemaHallSeats();

        char ch = 'A';
        int j = 10;
        int rowNumber = 1;
        for(int i = 1;i <= cinemahallSeatDTO.getNoOfClassicSeat();i++) {
            CinemaHallSeatsEntity cinemahallSeat = new CinemaHallSeatsEntity();
            if(j == 0) {
                j = 10;
                ch = 'A';
                rowNumber++;
            }
            cinemahallSeat.setCinemaHallSeatNumber(Integer.toString(rowNumber)+(ch++));
            cinemahallSeat.setCinemahallSeatPrice(500.00);
            cinemahallSeat.setCinemaHallSeatType(SeatTypeEnum.CLASSIC);
            cinemahallSeat.setCinemahall(cinemaHallEntity);
            cinemaHallSeatsEntityList.add(cinemahallSeat);
            j--;
        }

        for(int i = 1;i <= cinemahallSeatDTO.getNoOfPremiumSeat();i++) {
            CinemaHallSeatsEntity cinemahallSeat = new CinemaHallSeatsEntity();
            if(j == 0) {
                j = 10;
                ch = 'A';
                rowNumber++;
            }
            cinemahallSeat.setCinemaHallSeatNumber(Integer.toString(rowNumber)+(ch++));
            cinemahallSeat.setCinemahallSeatPrice(1500.00);
            cinemahallSeat.setCinemaHallSeatType(SeatTypeEnum.PREMIUM);
            cinemahallSeat.setCinemahall(cinemaHallEntity);
            cinemaHallSeatsEntityList.add(cinemahallSeat);
            j--;
        }

        for(int i = 1;i <= cinemahallSeatDTO.getNoOfReclinerSeat();i++) {
            CinemaHallSeatsEntity cinemahallSeat = new CinemaHallSeatsEntity();
            if(j == 0) {
                j = 10;
                ch = 'A';
                rowNumber++;
            }
            cinemahallSeat.setCinemaHallSeatNumber(Integer.toString(rowNumber)+(ch++));
            cinemahallSeat.setCinemahallSeatPrice(2500.00);
            cinemahallSeat.setCinemaHallSeatType(SeatTypeEnum.RECLINER);
            cinemahallSeat.setCinemahall(cinemaHallEntity);
            cinemaHallSeatsEntityList.add(cinemahallSeat);
            j--;
        }

        cinemaHallRepository.save(cinemaHallEntity);


        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("success");
        responseDTO.setMessage("Cinemahall seats added Successfully");

        return responseDTO;
    }

    @Override
    public List<CinemaHallSeatsEntity> getCinemahallSeats(Integer id) {
        return cinemaHallSeatRepository.findAllByCinemahall(id);
    }
}
