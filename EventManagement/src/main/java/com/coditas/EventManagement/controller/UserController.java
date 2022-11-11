package com.coditas.EventManagement.controller;

import com.coditas.EventManagement.dto.UserDto;
import com.coditas.EventManagement.entities.User;
import com.coditas.EventManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity registerUser(@RequestBody UserDto userDto){
        try {
            return new ResponseEntity<>(Optional.of(userService.registerUser(userDto)), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/loginUser")
    public ResponseEntity loginUser(@RequestBody User user){
        return userService.loginUser(user);
    }
}

