package com.coditas.EventManagement.service;

import com.coditas.EventManagement.entities.EventCategory;

import java.util.List;
import java.util.Optional;

public interface EventCategoryService {

    public EventCategory createEventCategory(EventCategory eventCategory);
    public void deleteEventCategory(Long categoryId);

    public List<EventCategory> fetchAllEventCategory();

    public EventCategory updateEventCategory(EventCategory eventCategory);

}
