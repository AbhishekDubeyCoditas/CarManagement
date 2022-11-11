package com.coditas.EventManagement.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private Long eventId;

    private String eventVenue;

    private String eventName;

    private String eventCity;

    private int eventVenueCapacity;

    private float eventBasePrice;

    private float pricePerPerson;

    private float averageRating;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_category_id")
    private EventCategory eventCategory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_organizer_organizer_id")
    private EventOrganizer eventOrganizer;


//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "customer_customer_id")
//    private Customer customer;


    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

}
