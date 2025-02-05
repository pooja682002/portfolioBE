package com.example.portfoliotask_backend.dto;


import com.example.portfoliotask_backend.model.Admin;
import java.util.UUID;

public class AdminDTO {
    private UUID id;
    private String username;
    private String password;

    public AdminDTO()
    {

    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
