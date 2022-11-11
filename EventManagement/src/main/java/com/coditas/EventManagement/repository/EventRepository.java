package com.coditas.EventManagement.repository;

import com.coditas.EventManagement.entities.Event;
import com.coditas.EventManagement.entities.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

    Event findByEventId(Long eventId);

    List<Event> findByEventOrganizer(EventOrganizer eventOrganizer);
}
