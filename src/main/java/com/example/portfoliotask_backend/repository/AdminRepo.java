package com.example.portfoliotask_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.portfoliotask_backend.model.Admin;


import java.util.UUID;

public interface AdminRepo extends JpaRepository<Admin, UUID> {
    Admin findByUsername(String username);
}