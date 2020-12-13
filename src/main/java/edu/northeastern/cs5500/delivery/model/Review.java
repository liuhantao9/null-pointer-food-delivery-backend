package edu.northeastern.cs5500.delivery.model;

import lombok.Data;

@Data
public class Review {
    private int stars;
    private String comment;

    public Review() {}

    public Review(String stars, String comment) throws Exception {
        this.stars = Integer.parseInt(stars);
        this.comment = comment;
    }
}
