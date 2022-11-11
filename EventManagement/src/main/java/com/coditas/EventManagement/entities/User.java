package com.coditas.EventManagement.entities;
import lombok.*;
import javax.persistence.*;
@Entity
@Table(name = "User_Details")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long userId;
    private String userEmail;
    private String userPassword;
    private String userRole;
}
