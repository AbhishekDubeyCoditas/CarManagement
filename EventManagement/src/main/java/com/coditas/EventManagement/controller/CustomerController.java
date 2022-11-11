package com.coditas.EventManagement.controller;


import com.coditas.EventManagement.dto.BookingDto;
import com.coditas.EventManagement.dto.FeedbackDto;
import com.coditas.EventManagement.entities.Event;
import com.coditas.EventManagement.service.BookingService;
import com.coditas.EventManagement.service.CustomerService;
import com.coditas.EventManagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    BookingService bookingService;

    @Autowired
    CustomerService customerService;

    @Autowired
    EventService eventService;



    @PostMapping(value = "/addEventBooking")
    public ResponseEntity addEventBooking(@RequestBody BookingDto bookingDto){
        try {
            return  new ResponseEntity(Optional.of(customerService.addEventBooking(bookingDto)), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/filterByPrice/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Event>> filterByEventPrice(@PathVariable float minPrice, @PathVariable float maxPrice){
        try {
            return new ResponseEntity(Optional.of(eventService.filterEventsByPrice(minPrice, maxPrice)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/filterByRating/{rating}")
    public ResponseEntity<List<Event>> filterByEventRating(@PathVariable float rating) {
        try {
            return new ResponseEntity(Optional.of(eventService.filterEventsByRating(rating)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/getEvents/{eventCategoryName}/{startDate}/{endDate}")
    public ResponseEntity<List<Event>> filterByEventCategory(@PathVariable String eventCategoryName, @PathVariable Date startDate, @PathVariable Date endDate) {
        try {
            return new ResponseEntity(Optional.of(eventService.filterByCategory(eventCategoryName,(Date) startDate,(Date) endDate)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/giveFeedback")
    public ResponseEntity addCustomerFeedback(@RequestBody FeedbackDto feedbackDto) {
        try {
            return new ResponseEntity<>(Optional.of(customerService.giveFeedback(feedbackDto)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

