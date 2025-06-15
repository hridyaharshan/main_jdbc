package com.example.javaspringboot_assignment.Service;

import com.example.javaspringboot_assignment.DTO.CustomerRegistrationDto;
import com.example.javaspringboot_assignment.DTO.ProviderRegistrationDto;
import com.example.javaspringboot_assignment.Entity.Customer;
import com.example.javaspringboot_assignment.Entity.ServiceProvider;
import com.example.javaspringboot_assignment.Entity.UserRole;
import com.example.javaspringboot_assignment.Repository.CustomerRepository;
import com.example.javaspringboot_assignment.Repository.ServiceProviderRepository;
import com.example.javaspringboot_assignment.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;
    private final ServiceProviderRepository providerRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public String registerCustomer(CustomerRegistrationDto dto) {
        Customer customer = new Customer();
        customer.setUsername(dto.getUsername());
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));

        customer.setRole(UserRole.CUSTOMER);
        customerRepository.save(customer);

        return jwtTokenProvider.generateToken(customer.getUsername(), customer.getRole());
    }

    public String registerProvider(ProviderRegistrationDto dto) {
        ServiceProvider provider = new ServiceProvider();
        provider.setUsername(dto.getUsername());
        provider.setPassword(passwordEncoder.encode(dto.getPassword()));
        provider.setRole(UserRole.PROVIDER);
        providerRepository.save(provider);

        return jwtTokenProvider.generateToken(provider.getUsername(), provider.getRole());
    }
}
