package com.coditas.EventManagement.repository;

import com.coditas.EventManagement.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByadminEmail(String adminEmail);
}
