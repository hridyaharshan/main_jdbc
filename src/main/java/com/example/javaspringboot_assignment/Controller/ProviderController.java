package com.example.javaspringboot_assignment.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/provider")
public class ProviderController {

    @GetMapping("/dashboard")
    public ResponseEntity<String> getDashboard(Authentication authentication) {
        String username = authentication.getName();  // Extracts username from token
        return ResponseEntity.ok("Welcome to the provider dashboard, " + username + "!");
    }
}

