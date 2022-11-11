package com.coditas.EventManagement.controller;

import com.coditas.EventManagement.dto.BookingDto;
import com.coditas.EventManagement.dto.EventDto;
import com.coditas.EventManagement.entities.Event;
import com.coditas.EventManagement.service.BookingService;
import com.coditas.EventManagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventOrganizer")
public class EventOrganizerController {

    @Autowired
    EventService eventService;

    @Autowired
    BookingService bookingService;



    @PostMapping(value = "/insertEvent")
    public ResponseEntity addEvent(@RequestBody EventDto eventdto) {
        try {
            return new ResponseEntity<>(Optional.of(eventService.addEvent(eventdto)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/getEvents/{organizerId}")
    public ResponseEntity<List<Event>> getEvents(@PathVariable Long eventOrganizerId) {
        try {
            List<Event> events = eventService.showEvents(eventOrganizerId);

            if(events.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(events, HttpStatus.OK);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/updateBooking/{bookingStatus}/{bookingId}")
    public ResponseEntity acceptOrDeclineEventBooking(@PathVariable String bookingStatus, @PathVariable Long bookingId) {
        try {
            return new ResponseEntity<>(Optional.of(bookingService.acceptOrDeclineEventBooking(bookingId, bookingStatus)), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/postponeBooking")
    public ResponseEntity postponeEventBooking(@RequestBody BookingDto bookingDto) {
        try {
            return new ResponseEntity<>(Optional.of(bookingService.postponeEventBooking(bookingDto)), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
