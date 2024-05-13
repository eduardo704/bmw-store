package com.eduardo.bmwstore.controllers.dtos;

import jakarta.validation.constraints.NotBlank;

public record OrderByUserEmailDTO(@NotBlank String email) {
}