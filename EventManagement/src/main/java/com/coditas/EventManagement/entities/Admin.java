package com.coditas.EventManagement.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false,updatable = false)
    private Long adminId;

    @Column(name = "Name", nullable = false)
    private String adminName;
    @Column(name = "Email", nullable = false)
    private String adminEmail;
    @Column(name = "Password", nullable = false)
    private String adminPassword;
    @Column(name = "Number", nullable = false)
    private long adminNumber;


}
