package com.coditas.EventManagement.repository;

import com.coditas.EventManagement.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findBycustomerEmail(String customerEmail);
}
