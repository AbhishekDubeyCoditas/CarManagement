package com.coditas.EventManagement.repository;

import com.coditas.EventManagement.entities.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory,Long> {

    Optional<EventCategory> findByCategoryName(String eventCategoryName);


}
