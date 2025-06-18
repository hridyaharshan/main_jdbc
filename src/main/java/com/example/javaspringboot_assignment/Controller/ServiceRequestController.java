package com.example.javaspringboot_assignment.Controller;

import com.example.javaspringboot_assignment.DTO.ServiceRequestDto;
import com.example.javaspringboot_assignment.Service.ServiceRequestService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import java.util.Collections;

@RestController
@RequestMapping("/api/customer/requests")
@RequiredArgsConstructor
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    @PostMapping
    public ResponseEntity<?> createRequest(
            @RequestBody ServiceRequestDto dto,
            Authentication authentication) {

        String username = authentication.getName();
        String result = serviceRequestService.createServiceRequest(dto, username);
        return ResponseEntity.ok(Collections.singletonMap("message", result));
    }
}
