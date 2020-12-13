package edu.northeastern.cs5500.delivery.model;

import lombok.Data;

@Data
public class Dish {
    String name;
    Float price;
    String imageUrl;

    public Dish() {}

    public Dish(String name, Float price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
