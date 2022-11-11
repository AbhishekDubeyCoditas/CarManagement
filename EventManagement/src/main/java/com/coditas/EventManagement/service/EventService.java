package com.coditas.EventManagement.service;

import com.coditas.EventManagement.dto.EventDto;
import com.coditas.EventManagement.entities.Event;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface EventService {

    Event addEvent(EventDto eventdto);
    List<Event> showEvents(Long eventOrganiserId);
    Event updateEvent(EventDto eventDto);
    void deleteEvent(Long eventId);
    List<Event> filterEventsByPrice(float minPrice, float maxPrice);
    List<Event> filterEventsByRating(float rating);
    List<Event> filterByCategory(String eventCategoryName, Date startDate, Date endDate);
}

