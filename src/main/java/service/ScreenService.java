package service;

import entity.Screen;
import entity.Seat;
import entity.Show;
import entity.Theatre;

public class ScreenService {
    public void addSeat(Screen screen, Seat s){
        screen.getSeats().add(s);
    }

    public void addShow(Screen screen, Show s){
        screen.getShows().add(s);
    }
}
