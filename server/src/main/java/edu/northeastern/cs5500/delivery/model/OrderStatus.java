package edu.northeastern.cs5500.delivery.model;

public enum OrderStatus {
    CREATED, // cancelled
    SUBMITTED,
    CANCELED,
    VALIDATED, // The restaurant has approved the order
    PREPARING, // The restaurant is cooking
    WAITING, // The order is waiting for a driver
    DELIVERING, // The order is on delivery, driver
    FINISHED
}
