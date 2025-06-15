package com.example.javaspringboot_assignment.Controller;

import com.example.javaspringboot_assignment.DTO.CustomerRegistrationDto;
import com.example.javaspringboot_assignment.DTO.ProviderRegistrationDto;
import com.example.javaspringboot_assignment.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerRegistrationDto dto) {
        String token = authService.registerCustomer(dto);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @PostMapping("/register/provider")
    public ResponseEntity<?> registerProvider(@RequestBody ProviderRegistrationDto dto) {
        try {
            String token = authService.registerProvider(dto);
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

}
