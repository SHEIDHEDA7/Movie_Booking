package entity;

import java.util.ArrayList;
import java.util.List;

public class City {
    private final long id;
    private final String name;
    private List<Theatre> theatres;
    public City(long id, String name){
        this.id = id;
        this.name = name;
        theatres = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Theatre> getTheatres() {
        return theatres;
    }
}
