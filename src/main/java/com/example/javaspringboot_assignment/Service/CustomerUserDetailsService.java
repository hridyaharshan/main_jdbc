package com.example.javaspringboot_assignment.Service;


import com.example.javaspringboot_assignment.Entity.BaseUser;
import com.example.javaspringboot_assignment.Entity.Customer;
import com.example.javaspringboot_assignment.Entity.ServiceProvider;
import com.example.javaspringboot_assignment.Repository.CustomerRepository;
import com.example.javaspringboot_assignment.Repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    private final ServiceProviderRepository providerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isPresent()) {
            Customer c = customer.get();
            return new User(
                    c.getUsername(),
                    c.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"))
            );
        }

        Optional<ServiceProvider> provider = providerRepository.findByUsername(username);
        if (provider.isPresent()) {
            ServiceProvider p = provider.get();
            return new User(
                    p.getUsername(),
                    p.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_PROVIDER"))
            );
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}



