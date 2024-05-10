package com.eduardo.bmwstore.converters;

import java.util.List;

import com.eduardo.bmwstore.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;

public class ProductConverter implements AttributeConverter<List<Product>, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Product> products) {
        try {
            return objectMapper.writeValueAsString(products);
        } catch (JsonProcessingException jpe) {
            // log.warn("Cannot convert Address into JSON");
            return null;
        }
    }

    @Override
    public List<Product> convertToEntityAttribute(String value) {
        try {
            TypeReference<List<Product>> jacksonTypeReference = new TypeReference<>() {
            };
            return objectMapper.readValue(value, jacksonTypeReference);
        } catch (JsonProcessingException e) {
            // log.warn("Cannot convert JSON into Address");
            return null;
        }
    }

}
