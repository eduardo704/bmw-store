package com.eduardo.bmwstore.dtos;


public record ApiErrorResponse(
    int errorCode,
    String description) {

}
