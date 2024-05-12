package com.movie_booking.bookmyshow.controller;

import com.movie_booking.bookmyshow.dto.ResponseDTO;
import com.movie_booking.bookmyshow.dto.ShowDTO;
import com.movie_booking.bookmyshow.entity.MovieEntity;
import com.movie_booking.bookmyshow.entity.ShowEntity;
import com.movie_booking.bookmyshow.entity.ShowSeatsEntity;
import com.movie_booking.bookmyshow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/show/v1")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<?> addShows(@RequestBody List<ShowDTO> showDTOList) {
        try {
            ResponseDTO responseDTO = showService.addShow(showDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllShowsOfMovie")
    public ResponseEntity<?> getAllShowsOfMovie(@RequestParam(name = "movieId", required = true) Integer movieId) {
        try {
            List<ShowDTO> shows = showService.getAllShowsOfMovie(movieId);
            return new ResponseEntity<>(shows, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addSeats")
    public ResponseEntity<?> addSeatsToParticularShow(@RequestParam(name = "showId", required = true) Integer showId) {
        try {
            ResponseDTO responseDTO = showService.addSeatsToParticularShow(showId);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getShowSeats")
    public ResponseEntity<?> getShowSeats(@RequestParam(name = "showId", required = true) Integer showId) {
        try {
            List<ShowSeatsEntity> showSeatsEntities = showService.getShowSeats(showId);
            return new ResponseEntity<>(showSeatsEntities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
