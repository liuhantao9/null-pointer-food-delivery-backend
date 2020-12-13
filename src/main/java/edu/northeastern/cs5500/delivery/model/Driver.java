package edu.northeastern.cs5500.delivery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Date;
import lombok.Data;

@Data
public class Driver implements Model {
    private String id, email, password, name, phone;
    private double longitude, latitude;

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
}
