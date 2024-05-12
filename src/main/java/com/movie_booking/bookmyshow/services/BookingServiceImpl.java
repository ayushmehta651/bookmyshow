package com.movie_booking.bookmyshow.services;

import com.movie_booking.bookmyshow.custom_exceptions.BadRequestException;
import com.movie_booking.bookmyshow.dto.BookingReponseDTO;
import com.movie_booking.bookmyshow.dto.BookingRequestDTO;
import com.movie_booking.bookmyshow.entity.BookingEntity;
import com.movie_booking.bookmyshow.entity.ShowEntity;
import com.movie_booking.bookmyshow.entity.ShowSeatsEntity;
import com.movie_booking.bookmyshow.entity.UserEntity;
import com.movie_booking.bookmyshow.mapper.BookingMapper;
import com.movie_booking.bookmyshow.repositories.BookingRepository;
import com.movie_booking.bookmyshow.repositories.ShowRepository;
import com.movie_booking.bookmyshow.repositories.ShowSeatRepository;
import com.movie_booking.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShowSeatRepository showSeatRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Override
    public BookingReponseDTO addBooking(BookingRequestDTO bookingDTO) {
        // get user from email
        Optional<UserEntity> user = userRepository.findByEmail(bookingDTO.getEmail());
        if(user.isEmpty()) {
            throw new BadRequestException("User doesn't exist with email: " + bookingDTO.getEmail());
        }
        Optional<ShowEntity> show = showRepository.findById(bookingDTO.getShowId());
        if(show.isEmpty()) {
            throw new BadRequestException("Show doesn't exist with id: " + bookingDTO.getShowId());
        }

        List<ShowSeatsEntity> showSeatsEntities = show.get().getShowSeats();

        showSeatsEntities =
                showSeatsEntities
                        .stream()
                        .filter(seat -> seat.getIsAvailable()
                                && bookingDTO.getSeatIds().contains(seat.getSeatId()))
                        .collect(Collectors.toList());

        if (showSeatsEntities.size() != bookingDTO.getSeatIds().size()) {
            throw new BadRequestException("Seats Not Available for Booking");
        }

        BookingEntity bookingEntity = BookingEntity.builder()
                .user(user.get())
                .show(show.get())
                .seats(showSeatsEntities)
                .build();

        float totalPrice = 0;
        StringBuilder allotedSeats = new StringBuilder();

        for (ShowSeatsEntity showSeatsEntity: showSeatsEntities) {
            showSeatsEntity.setIsAvailable(false);
            showSeatsEntity.setBooking_date(new Date());
            showSeatsEntity.setSeats(bookingEntity);

            totalPrice += (float) showSeatsEntity.getSeatPrice();
            allotedSeats.append(showSeatsEntity.getSeatNumber()).append(" ");
        }
        bookingEntity.setBookingPrice(totalPrice);
        bookingEntity.setAllottedSeats(String.valueOf(allotedSeats));
        bookingEntity.setBookingDate(new Date());

        user.get().getBookingEntities().add(bookingEntity);
        show.get().getShowBookings().add(bookingEntity);

        bookingRepository.save(bookingEntity);

        return BookingMapper.BookingToBookingDTO(bookingEntity);
    }
}
