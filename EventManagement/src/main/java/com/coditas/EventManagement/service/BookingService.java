package com.coditas.EventManagement.service;

import com.coditas.EventManagement.dto.BookingDto;
import com.coditas.EventManagement.entities.Booking;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {

    Booking acceptOrDeclineEventBooking(Long bookingId, String bookingStatus);
    Booking postponeEventBooking(BookingDto bookingDto);

}
