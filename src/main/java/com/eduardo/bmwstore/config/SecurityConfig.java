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
        // our private endpoints
        )
        .authenticationManager(authenticationManager)

        // We need jwt filter before the UsernamePasswordAuthenticationFilter.
        // Since we need every request to be authenticated before going through spring
        // security filter.
        // (UsernamePasswordAuthenticationFilter creates a
        // UsernamePasswordAuthenticationToken from a username and password that are
        // submitted in the HttpServletRequest.)
        // .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    //     return config.getAuthenticationManager();
    // }


}

// CORS(Cross-origin resource sharing) is just to avoid if you run javascript
// across different domains like if you execute JS on http://testpage.com and
// access http://anotherpage.com
// CSRF(Cross-Site Request Forgery)