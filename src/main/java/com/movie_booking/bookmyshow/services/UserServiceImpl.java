package com.movie_booking.bookmyshow.services;

import com.movie_booking.bookmyshow.entity.UserEntity;
import com.movie_booking.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> allUsers() {
        return new ArrayList<>(userRepository.findAll());
    }
}