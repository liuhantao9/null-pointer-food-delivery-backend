package edu.northeastern.cs5500.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {
    private String name;
    private double price;
    private int quantity;
}
