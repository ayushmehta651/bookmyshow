package com.movie_booking.bookmyshow.services;

import com.movie_booking.bookmyshow.custom_exceptions.BadRequestException;
import com.movie_booking.bookmyshow.dto.MovieDTO;
import com.movie_booking.bookmyshow.dto.ResponseDTO;
import com.movie_booking.bookmyshow.entity.MovieEntity;
import com.movie_booking.bookmyshow.mapper.MovieMapper;
import com.movie_booking.bookmyshow.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;


    @Override
    public List<MovieDTO> getAllMovies(String movie_name, List<String> movie_city, Date movie_date) {
        List<MovieEntity> movieEntityList = new ArrayList<>();
        if(movie_name != null) {
            movieEntityList = movieRepository.findAllByTitleContaining(movie_name);
        }
        if(!CollectionUtils.isEmpty(movie_city)) movieEntityList = movieRepository.findAllByCityIn(movie_city);
        if(movie_date != null) movieEntityList = movieRepository.findAllByDate(movie_date);
        if(movie_name == null && movie_date == null && movie_city == null) movieEntityList = movieRepository.findAll();

        return movieEntityList.stream().map(MovieMapper::movieToMovieDTO).collect(Collectors.toList());
    }

    @Override
    public ResponseDTO addMovies(List<MovieDTO> movieList) {
        movieList.forEach(movieDTO -> {
            if(movieRepository.findByTitle(movieDTO.getTitle()) != null) {
                throw new BadRequestException("Movie Already present with the same name");
            } else {
                MovieEntity movie = MovieMapper.movieDTOtoMovie(movieDTO);
                movieRepository.save(movie);
            }
        });
        ResponseDTO response = new ResponseDTO();
        response.setStatus("success");
        response.setMessage("All Movies added Successfully!");
        return response;
    }
}
