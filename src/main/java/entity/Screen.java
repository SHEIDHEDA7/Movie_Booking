package entity;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final long id;
    private final String name;
    private final Theatre theatre;
    private List<Seat> seats;
    private List<Show> shows;
    public Screen(long id, String name, Theatre theatre){
        this.id = id;
        this.name = name;
        this.theatre = theatre;
        seats = new ArrayList<>();
        shows = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public List<Show> getShows() {
        return shows;
    }
}
