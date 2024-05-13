package com.eduardo.bmwstore.controllers;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.bmwstore.dtos.LoginRequest;
import com.eduardo.bmwstore.dtos.LoginResponse;
import com.eduardo.bmwstore.dtos.SignUpRequest;
import com.eduardo.bmwstore.services.JwtService;
import com.eduardo.bmwstore.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignUpRequest requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        String token = this.jwtService.generateToken(request.email());
        return ResponseEntity.ok(new LoginResponse(request.email(), token));
    }

    @PostMapping(value = "/getInfoFromToken")
    public String login(@RequestHeader("Authorization") String bearerToken) {

        String email = this.jwtService.extractUsername(bearerToken);
        return email;
    }

}
