package com.coditas.EventManagement.service;

import com.coditas.EventManagement.dto.BookingDto;
import com.coditas.EventManagement.dto.FeedbackDto;
import com.coditas.EventManagement.entities.*;
import com.coditas.EventManagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImplementation implements CustomerService{

    @Autowired
    EventRepository eventRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EventBookingRepository eventBookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Override
    public Booking addEventBooking(BookingDto bookingDto) {
        Booking addBooking = new Booking();

        addBooking.setStartDate(bookingDto.getBookingStartDate());
        addBooking.setEndDate(bookingDto.getBookingEndDate());
        addBooking.setNumberOfGuest(bookingDto.getTotalBookedSeats());

        Event event = eventRepository.findByEventId(bookingDto.getEventId());
        float bookingTotalPrice = (event.getEventBasePrice() + (event.getPricePerPerson() * bookingDto.getTotalBookedSeats()));
        addBooking.setBookingTotalPrice(bookingTotalPrice);
        addBooking.setEvent(event);

        //from customer
        Customer customer = customerRepository.findById(bookingDto.getCustomerId()).orElse(null);
        addBooking.setCustomer(customer);

        addBooking.setBookingStatus("pending");
        return eventBookingRepository.save(addBooking);
    }

    @Override
    public void cancelEventBooking(Long bookingId) {
        Booking booking = eventBookingRepository.findById(bookingId).orElse(null);
        booking.setBookingStatus("cancelled");
        eventBookingRepository.save(booking);
    }

    @Override
    public Customer updateCustomerDetails(Customer customer) {
        Customer updatedCustomer = customerRepository.findById(customer.getCustomerId()).orElse(null);

        if(updatedCustomer != null) {
            updatedCustomer.setCustomerName(customer.getCustomerName());
            updatedCustomer.setCustomerName(customer.getCustomerName());
            return customerRepository.save(customer);
        } else {
            return null;
        }
    }

    @Override
    public User changePassword(String oldPassword, String newPassword) {
        User user = userRepository.findByUserPassword(oldPassword).orElse(null);

        if(user != null) {
            user.setUserPassword(newPassword);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public Feedback giveFeedback(FeedbackDto feedbackDto) {
        Feedback provideFeedback = new Feedback();

        provideFeedback.setFeedbackComment(feedbackDto.getFeedbackComment());
        provideFeedback.setFeedbackRating(feedbackDto.getFeedBackRating());

        Event event = (Event) eventRepository.findById(feedbackDto.getEventId()).orElse(null);
        provideFeedback.setEvent(event);

        Customer customer = customerRepository.findById(feedbackDto.getCustomerId()).orElse(null);
        provideFeedback.setCustomer(customer);

        return feedbackRepository.save(provideFeedback);
    }
}
