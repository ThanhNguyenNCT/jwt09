package com.Cybersoft.uniclub09.config;

import com.Cybersoft.uniclub09.filter.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomerAuthenProvider customerAuthenProvider;
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Using BCryptPasswordEncoder for password hashing
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager (HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf(AbstractHttpConfigurer::disable) // Tắt CSRF protection
//                .getSharedObject(AuthenticationManagerBuilder.class)
//                .authenticationProvider(customerAuthenProvider)
//                .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthorizationFilter authorizationFilter) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection
            .authorizeHttpRequests(request -> {
                request.requestMatchers("/auth/*").permitAll();
                request.requestMatchers("/files/*").permitAll();
                request.requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN");// Only allow ADMIN role to create products
                request.requestMatchers(HttpMethod.GET, "/products").permitAll();
                request.anyRequest().authenticated(); // Require authentication for all other requests
            })
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();


    }


}
