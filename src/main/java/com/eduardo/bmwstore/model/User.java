package com.eduardo.bmwstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Column(length = 100, nullable = false)
    private String name;

    @NotBlank
    @NotNull
    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @NotBlank
    @NotNull
    @Column(length = 100, nullable = true)
    private String password;

}
