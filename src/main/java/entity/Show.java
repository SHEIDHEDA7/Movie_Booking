package entity;

import entity.enums.ShowStatus;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Show {
    private final long id;
    private final Movie movie;
    private final Screen screen;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private ShowStatus showStatus;
    private HashMap<Long, ShowSeat> showSeats;

    public Show(long id, Movie movie, Screen screen, LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.start = start;
        this.end = end;
        this.showStatus = ShowStatus.SCHEDULED;
        initializeSeats();
    }

//  It will take seats from Screen and then create a showSeat(has show and seat details)
    private void initializeSeats(){
        showSeats = new HashMap<>();
        for(Seat s : screen.getSeats()){
            ShowSeat newSeat = new ShowSeat(this, s);
            showSeats.put(s.getId(), newSeat);
        }
    }

    public long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public ShowStatus getShowStatus() {
        return showStatus;
    }

    public HashMap<Long, ShowSeat> getShowSeats() {
        return showSeats;
    }
}
