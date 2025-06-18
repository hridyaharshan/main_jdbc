package com.example.javaspringboot_assignment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {
    @Id
    @GeneratedValue
    private Long id;

    private String category;           // e.g., "plumbing"
    private String description;
    private String location;           // Can later use GPS coordinates
    private LocalDate preferredDate;

    @ManyToOne
    private Customer customer;

    private String status = "PENDING"; // PENDING, ASSIGNED, COMPLETED
}
