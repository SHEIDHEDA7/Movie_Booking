package service;

import entity.Screen;
import entity.Theatre;

public class TheatreService {
    public void addScreen(Theatre t, Screen s){
        t.getScreens().add(s);
    }
    public void removeScreen(Theatre t, Screen s){
        if(!t.getScreens().contains(s)){
            throw new IllegalArgumentException("This city does not have given theatre");
        }else{
            t.getScreens().remove(s);
        }
    }
}
