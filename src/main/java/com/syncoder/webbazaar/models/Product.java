package com.syncoder.webbazaar.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String city;
    private String author;
}
