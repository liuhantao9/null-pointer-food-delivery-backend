package edu.northeastern.cs5500.delivery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class Restaurant implements Model {
    private String id, email, password, street, city, phone, name, url;
    private Integer zipcode;
    private double latitude, longitude;
    private List<Review> reviewsList;
    private List<Dish> menu;
    private boolean isOpen;
    private LocalTime open;
    private LocalTime close;
    private Float rating;

    public Restaurant() {
        this.reviewsList = new ArrayList<Review>();
        this.menu = new ArrayList<Dish>();
        this.rating = (float) 0;
    }

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'",
            timezone = "GMT")
    private Date created;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'",
            timezone = "GMT")
    private Date lastModified;

    @JsonIgnore
    public boolean isValid() {
        return !email.isEmpty() && !password.isEmpty();
    }

    public void addReview(Review review) {
        int size = reviewsList.size();
        this.rating = (this.rating * size + review.getStars()) / (size + 1);
        this.reviewsList.add(review);
    }
}
