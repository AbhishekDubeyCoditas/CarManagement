package com.coditas.EventManagement.service;


import com.coditas.EventManagement.entities.EventCategory;
import com.coditas.EventManagement.repository.EventCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventCategoryServiceImplementation implements EventCategoryService{

    @Autowired
    EventCategoryRepository eventCategoryRepository;


    @Override
    public EventCategory createEventCategory(EventCategory eventCategory) {
        return eventCategoryRepository.save(eventCategory);
    }

    @Override
    public void deleteEventCategory(Long categoryId) {
            eventCategoryRepository.deleteById(categoryId);
    }

    @Override
    public List<EventCategory> fetchAllEventCategory() {
        return eventCategoryRepository.findAll();
    }


    public EventCategory updateEventCategory(EventCategory eventCategory) {
        EventCategory existingCategory = eventCategoryRepository.findById(eventCategory.getCategoryId()).orElse(null);
        if (existingCategory != null) {
            existingCategory.setCategoryName(eventCategory.getCategoryName());
            return eventCategoryRepository.save(existingCategory);
        }
        return null;
    }
}
