package com.coditas.EventManagement.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "Organizer")
public class EventOrganizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizer_id", nullable = false)
    private Long organizerId;

    private String organizerName;

    private String organizerEmail;

    private long organizerNumber;

    private String organizerPassword;

    @OneToMany(mappedBy = "eventOrganizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

}
