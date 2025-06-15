package com.example.javaspringboot_assignment.Controller;



import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @GetMapping("/dashboard")
    public ResponseEntity<String> getDashboard(Authentication authentication) {
        String username = authentication.getName();  // Extracts username from token
        return ResponseEntity.ok("Welcome to the customer dashboard, " + username + "!");
    }
}
