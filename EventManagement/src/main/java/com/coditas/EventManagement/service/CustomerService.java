package com.coditas.EventManagement.service;

import com.coditas.EventManagement.dto.BookingDto;
import com.coditas.EventManagement.dto.FeedbackDto;
import com.coditas.EventManagement.entities.Booking;
import com.coditas.EventManagement.entities.Customer;
import com.coditas.EventManagement.entities.Feedback;
import com.coditas.EventManagement.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    Booking addEventBooking(BookingDto bookingDto);
    void cancelEventBooking(Long bookingId);
    Customer updateCustomerDetails(Customer customer);
    User changePassword(String oldPassword, String newPassword);
    Feedback giveFeedback(FeedbackDto feedbackDto);
}
