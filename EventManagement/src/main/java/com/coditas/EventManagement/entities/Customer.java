package com.coditas.EventManagement.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "customer_Name", nullable = false)
    private String customerName;

    @Column(name = "customer_Email", nullable = false)
    private String customerEmail;

    @Column(name = "customer_Password", nullable = false)
    private String customerPassword;

    @Column(name = "customer_PhoneNumber", nullable = false)
    private Long customerPhoneNumber;

    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();


}
