package com.coditas.EventManagement.service;

import com.coditas.EventManagement.dto.UserDto;
import com.coditas.EventManagement.entities.Admin;
import com.coditas.EventManagement.entities.Customer;
import com.coditas.EventManagement.entities.EventOrganizer;
import com.coditas.EventManagement.entities.User;
import com.coditas.EventManagement.repository.AdminRepository;
import com.coditas.EventManagement.repository.CustomerRepository;
import com.coditas.EventManagement.repository.EventOrganizerRepository;
import com.coditas.EventManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventOrganizerRepository eventOrganizerRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public User registerUser(UserDto userDto) {

        if (userDto.getUserRole().equals("eventOrganiser")) {
            EventOrganizer eventOrganizer = new EventOrganizer();
            eventOrganizer.setOrganizerName(userDto.getUserName());
            eventOrganizer.setOrganizerNumber(userDto.getUserNumber());
            eventOrganizer.setOrganizerEmail(userDto.getUserEmail());
            eventOrganizer.setOrganizerPassword(userDto.getUserPassword());
            eventOrganizerRepository.save(eventOrganizer);
        } else if (userDto.getUserRole().equals("admin")) {
            Admin admin = new Admin();
            admin.setAdminName(userDto.getUserName());
            admin.setAdminNumber(userDto.getUserNumber());
            admin.setAdminEmail(userDto.getUserEmail());
            admin.setAdminPassword(userDto.getUserPassword());
            adminRepository.save(admin);
        } else if (userDto.getUserRole().equals("customer")) {
            Customer customer = new Customer();
            customer.setCustomerName(userDto.getUserName());
            customer.setCustomerPhoneNumber(userDto.getUserNumber());
            customer.setCustomerEmail(userDto.getUserEmail());
            customer.setCustomerPassword(userDto.getUserPassword());
            customerRepository.save(customer);
        }

        User user = new User();
        user.setUserEmail(userDto.getUserEmail());
        user.setUserPassword(userDto.getUserPassword());
        user.setUserRole(userDto.getUserRole());
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity loginUser(User user) {
        User foundUser = null;
        foundUser = userRepository.findByuserEmail(user.getUserEmail());

        if (foundUser != null) {
            if (user.getUserPassword().equals(foundUser.getUserPassword())) {

                try {
                    if (foundUser.getUserRole().equals("admin")) {
                        Admin adminUser = adminRepository.findByadminEmail(user.getUserEmail());
                        return new ResponseEntity(Optional.of(adminUser), HttpStatus.OK);
                    } else if (foundUser.getUserRole().equals("eventOrganiser")) {
                        EventOrganizer eventOrganizerUser = eventOrganizerRepository.findByorganizerEmail(user.getUserEmail());
                        return new ResponseEntity(Optional.of(eventOrganizerUser), HttpStatus.OK);
                    } else if (foundUser.getUserRole().equals("customer")) {
                        Customer customerUser = customerRepository.findBycustomerEmail(user.getUserEmail());
                        return new ResponseEntity(Optional.of(customerUser), HttpStatus.OK);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }
}
