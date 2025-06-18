package com.example.javaspringboot_assignment.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceProvider {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    private String category; // 🛠️ e.g., "plumbing", "electrician", etc.

    @ElementCollection
    private List<LocalDate> availableDates;


    @Enumerated(EnumType.STRING)
    private UserRole role;

}


