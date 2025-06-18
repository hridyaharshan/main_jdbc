package com.example.javaspringboot_assignment.Service;

import com.example.javaspringboot_assignment.DTO.ServiceRequestDto;
import com.example.javaspringboot_assignment.Entity.Customer;
import com.example.javaspringboot_assignment.Entity.ServiceProvider;
import com.example.javaspringboot_assignment.Entity.ServiceRequest;
import com.example.javaspringboot_assignment.Repository.CustomerRepository;
import com.example.javaspringboot_assignment.Repository.ServiceProviderRepository;
import com.example.javaspringboot_assignment.Repository.ServiceRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {

    private final CustomerRepository customerRepository;
    private final ServiceRequestRepository requestRepository;

    public String createServiceRequest(ServiceRequestDto dto, String username) {
        // fetched the detailes from repo
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));

        // Create the service request
        ServiceRequest request = new ServiceRequest();
        request.setCategory(dto.getCategory());
        request.setDescription(dto.getDescription());
        request.setLocation(dto.getLocation());
        request.setPreferredDate(dto.getPreferredDate());
        request.setCustomer(customer);
        request.setStatus("PENDING");

        // Save to DB
        requestRepository.save(request);

        return "Service Request created successfully.";
    }
}
