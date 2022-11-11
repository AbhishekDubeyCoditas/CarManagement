package com.coditas.EventManagement.entities;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class EventCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false,updatable = false)
    private Long categoryId;

    @Column(name = "Name", nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "eventCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

}
