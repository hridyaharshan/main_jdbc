package com.example.javaspringboot_assignment.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProviderRegistrationDto {
    private String username;
    private String password;
    private String category;
    private List<LocalDate> availableDates;
}
