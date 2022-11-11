package com.coditas.EventManagement.repository;

import com.coditas.EventManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByuserEmail(String userEmail);
    Optional<User> findByUserPassword(String userPassword);

}
