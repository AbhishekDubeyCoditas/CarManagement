package com.coditas.EventManagement.service;

import com.coditas.EventManagement.dto.EventDto;
import com.coditas.EventManagement.entities.Event;
import com.coditas.EventManagement.entities.EventCategory;
import com.coditas.EventManagement.entities.EventOrganizer;
import com.coditas.EventManagement.repository.EventCategoryRepository;
import com.coditas.EventManagement.repository.EventOrganizerRepository;
import com.coditas.EventManagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImplementation implements EventService{

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @Autowired
    EventOrganizerRepository eventOrganizerRepository;


    @Override
    public Event addEvent(EventDto eventdto) {
       Event insertEvent = new Event();

       insertEvent.setEventName(eventdto.getEventName());
       insertEvent.setEventVenue(eventdto.getEventVenue());
       insertEvent.setEventCity(eventdto.getEventCity());
       insertEvent.setEventVenueCapacity(eventdto.getEventVenueCapacity());
       insertEvent.setEventBasePrice(eventdto.getEventBasePrice());
       insertEvent.setPricePerPerson(eventdto.getEventPricePerPerson());


        EventCategory foundEventCategory = eventCategoryRepository.findById(eventdto.getEventCategoryId()).orElse(null);
        insertEvent.setEventCategory(foundEventCategory);


        EventOrganizer foundEventOrganizer = eventOrganizerRepository.findById(eventdto.getEventOrganizerId()).orElse(null);
        insertEvent.setEventOrganizer(foundEventOrganizer);
        return eventRepository.save(insertEvent);
    }

    @Override
    public List<Event> showEvents(Long organizerId) {
        EventOrganizer eventOrganizer = eventOrganizerRepository.findById(organizerId).orElse(null);
        List<Event> events = eventRepository.findByEventOrganizer(eventOrganizer);

        return events;
    }

    @Override
    public Event updateEvent(EventDto eventDto) {
       Event foundEvent = eventRepository.findById(eventDto.getEventId()).orElse(null);

       if(foundEvent != null) {
           foundEvent.setEventBasePrice(eventDto.getEventBasePrice());
           foundEvent.setEventCity(eventDto.getEventCity());
           foundEvent.setEventName(eventDto.getEventName());
           foundEvent.setPricePerPerson(eventDto.getEventPricePerPerson());
           foundEvent.setEventVenue(eventDto.getEventVenue());
           foundEvent.setEventVenueCapacity(eventDto.getEventVenueCapacity());

           return eventRepository.save(foundEvent);
       } else {
           return null;
       }
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> filterEventsByPrice(float minPrice, float maxPrice) {
        return eventRepository.findAll().stream().filter(event -> (event.getEventBasePrice()>=minPrice) && (event.getEventBasePrice()<=maxPrice)).collect(Collectors.toList());
    }

    @Override
    public List<Event> filterEventsByRating(float rating) {
        return eventRepository.findAll().stream().filter(event -> event.getAverageRating() >= rating).collect(Collectors.toList());
    }

    @Override
    public List<Event> filterByCategory(String eventCategoryName, Date startDate, Date endDate) {
        EventCategory eventCategory = eventCategoryRepository.findByCategoryName(eventCategoryName).orElse(null);
        List<Event> eventsByCategory = eventRepository.findAll().stream().filter(event -> (event.getEventCategory().equals(eventCategory))).collect(Collectors.toList());
        return eventsByCategory.stream().filter(event -> event.getBookings().stream().filter(booking -> booking.getStartDate().before((java.sql.Date) startDate)).isParallel()).collect(Collectors.toList());
    }



}
