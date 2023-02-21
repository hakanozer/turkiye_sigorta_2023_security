package com.works.repositories;

import com.works.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmailEqualsIgnoreCase(String email);

    @Query(value = "select * from admin where email = ?1 and password = ?2", nativeQuery = true)
    Optional<Admin> adminLogin(String email, String password);


}