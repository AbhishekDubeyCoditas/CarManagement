package com.coditas.EventManagement.dto;

import lombok.Data;

@Data
public class UserDto {

    private String userEmail;
    private String userPassword;
    private String userName;
    private long userNumber;
    private String userRole;
}
