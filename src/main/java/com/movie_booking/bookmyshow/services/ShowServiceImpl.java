package com.movie_booking.bookmyshow.services;

import com.movie_booking.bookmyshow.custom_exceptions.BadRequestException;
import com.movie_booking.bookmyshow.dto.ResponseDTO;
import com.movie_booking.bookmyshow.dto.ShowDTO;
import com.movie_booking.bookmyshow.entity.*;
import com.movie_booking.bookmyshow.enums.SeatTypeEnum;
import com.movie_booking.bookmyshow.mapper.ShowMapper;
import com.movie_booking.bookmyshow.repositories.CinemaHallRepository;
import com.movie_booking.bookmyshow.repositories.MovieRepository;
import com.movie_booking.bookmyshow.repositories.ShowRepository;
import com.movie_booking.bookmyshow.repositories.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService{

    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CinemaHallRepository cinemaHallRepository;
    @Autowired
    ShowSeatRepository showSeatRepository;
    @Override
    public ResponseDTO addShow(List<ShowDTO> showDTOList) {

        showDTOList.forEach(showDTO -> {
            ShowEntity showEntity = ShowMapper.showDTOtoShow(showDTO);

            Optional<MovieEntity> movieEntity = movieRepository.findById(showDTO.getMovieId());
            if(movieEntity.isEmpty()) {
                throw new BadRequestException("Movie doesn't exist with that id: " + showDTO.getMovieId());
            }
            MovieEntity movie = movieEntity.get();

            Optional<CinemaHallEntity> cinemaHallEntity = cinemaHallRepository.findById(showDTO.getCinemahallId());
            if(cinemaHallEntity.isEmpty()) {
                throw new BadRequestException("No Theatre exist with id: " + showDTO.getCinemahallId());
            }
            CinemaHallEntity cinemaHall = cinemaHallEntity.get();

            showEntity.setMovie(movie);
            showEntity.setCinemahall(cinemaHall);
            showRepository.save(showEntity);

            cinemaHall.getShowEntityList().add(showEntity);
            cinemaHallRepository.save(cinemaHall);

            movie.getShows().add(showEntity);
            movieRepository.save(movie);
        });

        ResponseDTO response = new ResponseDTO();
        response.setStatus("success");
        response.setMessage("All Shows added Successfully!");
        return response;
    }

    public List<ShowDTO> getAllShowsOfMovie(Integer movieId) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(movieId);
        if(movieEntity.isEmpty()) {
            throw new BadRequestException("Movie doesn't exist with that id");
        }
        List<ShowEntity> show = showRepository.getAllShowsOfMovie(movieId);
        return show.stream().map(ShowMapper::showToShowDTO).collect(Collectors.toList());
    }

    @Override
    public ResponseDTO addSeatsToParticularShow(Integer showId) {
        Optional<ShowEntity> show = showRepository.findById(showId);
        if(show.isEmpty()) {
            throw new BadRequestException("No shows exist with id : " + showId);
        }
        ShowEntity showEntity = show.get();
        CinemaHallEntity cinemaHallEntity = showEntity.getCinemahall();

        List<CinemaHallSeatsEntity> cinemaHallSeatsEntityList = cinemaHallEntity.getCinemaHallSeats();
        List<ShowSeatsEntity> showSeatsEntities = showEntity.getShowSeats();

        for(CinemaHallSeatsEntity cinemaHallSeats: cinemaHallSeatsEntityList) {
            ShowSeatsEntity showSeatsEntity = new ShowSeatsEntity();
            showSeatsEntity.setSeatNumber(cinemaHallSeats.getCinemaHallSeatNumber());
            if(cinemaHallSeats.getCinemaHallSeatType() == SeatTypeEnum.CLASSIC) {
                showSeatsEntity.setSeatType(SeatTypeEnum.CLASSIC);
                showSeatsEntity.setSeatPrice(cinemaHallSeats.getCinemahallSeatPrice());
            }
            else if(cinemaHallSeats.getCinemaHallSeatType() == SeatTypeEnum.PREMIUM) {
                showSeatsEntity.setSeatType(SeatTypeEnum.PREMIUM);
                showSeatsEntity.setSeatPrice(cinemaHallSeats.getCinemahallSeatPrice());
            }
            else {
                showSeatsEntity.setSeatType(SeatTypeEnum.RECLINER);
                showSeatsEntity.setSeatPrice(cinemaHallSeats.getCinemahallSeatPrice());
            }
            showSeatsEntity.setShow(showEntity);
            showSeatsEntity.setIsAvailable(true);
            showSeatsEntities.add(showSeatsEntity);
        }

        showRepository.save(showEntity);

        ResponseDTO response = new ResponseDTO();
        response.setStatus("success");
        response.setMessage("All Shows seats added Successfully!");
        return response;
    }

    @Override
    public List<ShowSeatsEntity> getShowSeats(Integer showId) {
       Optional<ShowEntity> showSeatsEntity = showRepository.findById(showId);
       if(showSeatsEntity.isEmpty()) {
           throw new BadRequestException("Show doesn't exist with id: + " + showId);
       }
       return showSeatRepository.findAllByShowId(showId);
    }
}
