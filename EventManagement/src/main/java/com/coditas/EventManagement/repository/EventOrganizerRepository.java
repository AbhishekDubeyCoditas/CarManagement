package com.coditas.EventManagement.repository;

import com.coditas.EventManagement.entities.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventOrganizerRepository extends JpaRepository<EventOrganizer, Long> {

    EventOrganizer findByorganizerEmail(String eventorganizerEmail);
    List<EventOrganizer> findAll();
}
