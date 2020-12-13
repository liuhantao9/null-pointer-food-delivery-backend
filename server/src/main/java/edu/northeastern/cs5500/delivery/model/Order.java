package edu.northeastern.cs5500.delivery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class Order implements Model {
    private String id;
    private String userId;
    private String driverId;
    private String restaurantId;
    private OrderStatus orderStatus;
    // private OrderItem[] orderItems;
    private List<OrderItem> orderItems;
    private double tax;
    private double deliveryFee;
    private double totalPrice;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int zip;
    private String address;

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
        return !restaurantId.isEmpty() && !userId.isEmpty() && !orderItems.isEmpty();
    }
}

/*
post: /order
{
    "id": "id123",
    "userId": "userId123",
    "driverId": "driverid123",
    "restaurantId": "restaurantId123",
    "orderStatus": "SUBMITTED",
    "orderItems": [
        {
            "name": "rice",
            "price": 10.2,
            "quantity": 5
        },
        {
            "name": "noodles",
            "price": 15.2,
            "quantity": 2
        }
    ],
    "tax": 5.5,
    "deliveryFee": 2.5,
    "totalPrice": 27.9,
    "firstName": "firstNameBo",
    "lastName": "lastNameNiu",
    "phoneNumber": "2066367965",
    "zip": 98115,
    "created": "2011-10-05T14:48:00.000Z",
    "lastModified": "2011-10-05T15:48:00.000Z"
}

post: /user/ordersbydate
{
    "startDate": "2010-10-05T14:48:00.000Z",
    "endDate": "2012-10-05T14:48:00.000Z",
    "userId": "userId456"
}

post: /driver/ordersbydate
{
    "startDate": "2010-10-05T14:48:00.000Z",
    "endDate": "2012-10-05T14:48:00.000Z",
    "driverId": "driverid123"
}

post: /restaurant/ordersbydate
{
    "startDate": "2010-10-05T14:48:00.000Z",
    "endDate": "2012-10-05T14:48:00.000Z",
    "restaurantId": "restaurantId123"
}

 */
