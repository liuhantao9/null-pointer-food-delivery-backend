package edu.northeastern.cs5500.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Customer implements Model {
    private String id, email, password, address, avatar, phone, name;
    private Payment payment;

    @JsonIgnore
    public boolean isValid() {
        return !email.isEmpty() && !password.isEmpty();
    }
}
