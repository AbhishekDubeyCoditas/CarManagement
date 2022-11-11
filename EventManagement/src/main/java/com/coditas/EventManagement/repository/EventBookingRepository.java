package com.coditas.EventManagement.repository;


import com.coditas.EventManagement.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventBookingRepository extends JpaRepository<Booking,Long> {
}
