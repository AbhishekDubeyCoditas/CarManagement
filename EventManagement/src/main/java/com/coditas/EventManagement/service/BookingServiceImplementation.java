package com.coditas.EventManagement.service;

import com.coditas.EventManagement.dto.BookingDto;
import com.coditas.EventManagement.entities.Booking;
import com.coditas.EventManagement.repository.EventBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImplementation implements BookingService{

    @Autowired
    EventBookingRepository eventBookingRepository;

    @Override
    public Booking acceptOrDeclineEventBooking(Long bookingId, String bookingStatus) {
        Booking foundBooking = eventBookingRepository.findById(bookingId).orElse(null);

        if(foundBooking != null) {
            if(bookingStatus.equalsIgnoreCase("accepted")) {
                foundBooking.setBookingStatus("accepted");
                return eventBookingRepository.save(foundBooking);
            } else {
                foundBooking.setBookingStatus("declined");
                return eventBookingRepository.save(foundBooking);
            }
        } else {
            return null;
        }
    }

    @Override
    public Booking postponeEventBooking(BookingDto bookingDto) {
        Booking foundBooking = eventBookingRepository.findById(bookingDto.getBookingId()).orElse(null);

        if(foundBooking != null) {
            foundBooking.setBookingStatus("pending");
            foundBooking.setStartDate((java.sql.Date) bookingDto.getBookingStartDate());
            foundBooking.setEndDate((java.sql.Date) bookingDto.getBookingEndDate());
            return eventBookingRepository.save(foundBooking);
        } else {
            return null;
        }
    }
}

