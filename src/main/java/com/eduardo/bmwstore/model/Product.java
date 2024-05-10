package com.eduardo.bmwstore.model;

import lombok.Data;

@Data
public class Product {

    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;
    
}
