package com.movie_booking.bookmyshow.services;

import com.movie_booking.bookmyshow.dto.LoginUserDTO;
import com.movie_booking.bookmyshow.dto.RegisterUserDTO;
import com.movie_booking.bookmyshow.entity.UserEntity;
import com.movie_booking.bookmyshow.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
        private final UserRepository userRepository;

        private final PasswordEncoder passwordEncoder;

        private final AuthenticationManager authenticationManager;

        public AuthenticationService(
                UserRepository userRepository,
                AuthenticationManager authenticationManager,
                PasswordEncoder passwordEncoder
        ) {
            this.authenticationManager = authenticationManager;
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }

    public UserEntity signup(RegisterUserDTO input) {
        UserEntity user = UserEntity.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword())).build();

        return userRepository.save(user);
    }

    public UserEntity authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
