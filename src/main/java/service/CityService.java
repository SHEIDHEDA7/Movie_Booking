package service;

import entity.*;
import entity.enums.ShowStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CityService {
    public void addTheatre(City c, Theatre t){
        c.getTheatres().add(t);
    }
    public void removeTheatre(City c, Theatre t){
         if(!c.getTheatres().contains(t)){
             throw new IllegalArgumentException("This city does not have given theatre");
         }else{
             c.getTheatres().remove(t);
         }
    }
    public List<Theatre> getTheatres(City c){
        return c.getTheatres();
    }
    public List<Show> getAllShowsInCity(City c, LocalDate date){
        List<Show> shows = new ArrayList<>();
        for(Theatre t : c.getTheatres()){
            for(Screen screen : t.getScreens()){
                for(Show show : screen.getShows()){
                    if(show.getEnd().toLocalDate().isBefore(date)){
                        continue;
                    }
                    if(show.getShowStatus() == ShowStatus.CANCELLED || show.getShowStatus() == ShowStatus.COMPLETED){
                        continue;
                    }
                    shows.add(show);
                }
            }
        }
        return shows;
    }

    public List<Theatre> moviesInCity(Movie movie, City city){
        return city.getTheatres().stream()
                .filter(theatre ->
                        theatre.getScreens().stream().anyMatch(
                                screen -> screen.getShows().stream().anyMatch(
                                        show -> show.getMovie().getTitle().equalsIgnoreCase(
                                                        movie.getTitle().toLowerCase()
                                                )
                                )
                        )
                )
                .toList();
    }

}
