package com.coditas.EventManagement.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false,updatable = false)
    private long bookingId;

    private Date startDate;

    private Date endDate;

    private float totalPrice;

    private int numberOfGuest;

    private String bookingStatus;

    private float bookingTotalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_event_id")
    private Event event;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_customer_id")
    private Customer customer;


}
