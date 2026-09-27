package service;

import entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowService {
    public void addShowSeat(ShowSeat showSeat){
        HashMap<Long, ShowSeat> seats = showSeat.getShow().getShowSeats();
        Seat seat = showSeat.getSeat();
        seats.put(seat.getId(), showSeat);
    }

    public List<Show> getShows(Movie movie, City city, LocalDate date) {
        List<Show> shows = new ArrayList<>();
        for(Theatre theatre : city.getTheatres()){
            for(Screen screen : theatre.getScreens()){
                for(Show show : screen.getShows()){
                    if(show.getMovie().equals(movie) && show.getStart().toLocalDate().equals(date)){
                        shows.add(show);
                    }
                }
            }
        }
        return shows;
    }
}
