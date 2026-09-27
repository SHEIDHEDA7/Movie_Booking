package entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final long id;
    private final String name;
    private final String email;
    private final String phone;
    List<Booking> bookings;

    public User(long id, String name, String email, String phone){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        bookings = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
