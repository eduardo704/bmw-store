package com.eduardo.bmwstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {



  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager)
      throws Exception {
    return http
        .cors(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // Set permissions on endpoints
        .authorizeHttpRequests(auth -> auth
        
            // our public endpoints
            // .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
            // .requestMatchers(HttpMethod.POST, "/api/auth/login/**").permitAll()
            // .requestMatchers(HttpMethod.GET, "/authentication-docs/**").permitAll()
            .anyRequest().permitAll()

        )
        .authenticationManager(authenticationManager)


        // .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }


}
