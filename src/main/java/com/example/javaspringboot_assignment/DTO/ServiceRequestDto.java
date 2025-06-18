package com.example.javaspringboot_assignment.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ServiceRequestDto {
    private String category;
    private String description;
    private String location;
    private LocalDate preferredDate;
}
