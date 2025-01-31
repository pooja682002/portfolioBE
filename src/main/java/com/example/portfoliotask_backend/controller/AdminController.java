package com.example.portfoliotask_backend.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.portfoliotask_backend.dto.AdminDTO;
import com.example.portfoliotask_backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminDTO adminDTO) {
        String result = adminService.registerAdmin(adminDTO);

        if (result.equals("User already exists")) {
            return ResponseEntity.status(400).body(result); // Bad Request if user already exists
        } else if (result.equals("User registered successfully")) {
            return ResponseEntity.status(201).body(result); // Created
        } else {
            return ResponseEntity.status(500).body("Something went wrong"); // Internal Server Error
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin() {
        return ResponseEntity.ok("Login Successful"); // If authentication passes, Spring Security handles login
    }

}





