package entity;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private final long id;
    private final String name;
    private final String address;
    private final City city;
    private List<Screen> screens;

    public Theatre(long id, String name, String address, City city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        screens = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public City getCity() {
        return city;
    }

    public List<Screen> getScreens() {
        return screens;
    }
}
