package edu.northeastern.cs5500.delivery.model;

import lombok.Data;

@Data
public class Payment {
    private String cardNumber, expDate, holderName, cvc;
}
