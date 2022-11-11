package com.coditas.EventManagement.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", nullable = false)
    private Long feedbackId;

    @Lob
    private String feedbackComment;

    private float feedbackRating;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "customer_customer_id")
    private Customer customer;

}
