package com.movie_booking.bookmyshow.controller;

import com.movie_booking.bookmyshow.dto.CinemahallDTO;
import com.movie_booking.bookmyshow.dto.CinemahallSeatDTO;
import com.movie_booking.bookmyshow.dto.ResponseDTO;
import com.movie_booking.bookmyshow.entity.CinemaHallEntity;
import com.movie_booking.bookmyshow.entity.CinemaHallSeatsEntity;
import com.movie_booking.bookmyshow.repositories.CinemaHallRepository;
import com.movie_booking.bookmyshow.services.CinemahallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cinemahall/v1")
public class CinemaHallController {

    @Autowired
    CinemahallService cinemahallService;
    @PostMapping("/add")
    public ResponseEntity<?> addCinemahall(@RequestBody List<CinemahallDTO> cinemahallDTOList) {
        try {
            ResponseDTO responseDTO = cinemahallService.addCinemahall(cinemahallDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addCinemahallSeats")
    public ResponseEntity<?> addCinemahallSeats(@RequestBody CinemahallSeatDTO cinemahallSeatDTO) {
        try {
            ResponseDTO responseDTO = cinemahallService.addCinemahallSeats(cinemahallSeatDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getSeats")
    public ResponseEntity<?> getCinemahallSeats(@RequestParam(value = "cinemahall_id", required = true) Integer cinemahall_id) {
        try {
            List<CinemaHallSeatsEntity> res = cinemahallService.getCinemahallSeats(cinemahall_id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
