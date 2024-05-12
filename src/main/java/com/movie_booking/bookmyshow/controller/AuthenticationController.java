package com.movie_booking.bookmyshow.controller;

import com.movie_booking.bookmyshow.config.JwtService;
import com.movie_booking.bookmyshow.dto.LoginResponseDTO;
import com.movie_booking.bookmyshow.dto.LoginUserDTO;
import com.movie_booking.bookmyshow.dto.RegisterUserDTO;
import com.movie_booking.bookmyshow.entity.UserEntity;
import com.movie_booking.bookmyshow.services.AuthenticationService;
import com.movie_booking.bookmyshow.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth/v1")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> register(@Valid @RequestBody RegisterUserDTO registerUserDto) {
        UserEntity registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDTO loginResponse = LoginResponseDTO.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime()).build();

        return ResponseEntity.ok(loginResponse);
    }
}

