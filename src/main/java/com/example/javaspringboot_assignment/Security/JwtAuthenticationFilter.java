package com.example.javaspringboot_assignment.Security;



import com.example.javaspringboot_assignment.Service.CustomerUserDetailsService;
import com.example.javaspringboot_assignment.util.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;



/// CHECKS IF THE INCOMING HTTP REQUESTS ARE MADE BY AN AUTHENTICATED USER USING JWT TOKEN

@Component

@RequiredArgsConstructor
//for the constructor that accepts final fields
public class JwtAuthenticationFilter extends OncePerRequestFilter {
//authenticationfilter-check if it contains a valid jwt json token
    private final JwtTokenProvider jwtTokenProvider;//responsible for genearting,extracting and validating jwt

    private final CustomerUserDetailsService userDetailsService;//for loading the userdetails


    //checking for valid token before passing on
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        //basicaly used to authenticate without much complex name and password each
        String authHeader = request.getHeader("Authorization");


        //null then skip jwt processing
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //removing the bearer and getting only the token
        String token = authHeader.substring(7);
        String username = jwtTokenProvider.extractUsername(token);

        //username is not null and nnot authenticated then proced further
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenProvider.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        //passes the request to the next filter present
        filterChain.doFilter(request, response);
    }
}

