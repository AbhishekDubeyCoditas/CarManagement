package com.coditas.EventManagement.service;

import com.coditas.EventManagement.dto.UserDto;
import com.coditas.EventManagement.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService{

    User registerUser(UserDto userDto);
    ResponseEntity loginUser(User user);

}
