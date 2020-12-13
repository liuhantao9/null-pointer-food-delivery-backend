package edu.northeastern.cs5500.delivery.controller.util;

import lombok.Data;

@Data
public class RestaurantBrief {
    private String id;
    private String name;
    private String url;

    public RestaurantBrief(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
}
