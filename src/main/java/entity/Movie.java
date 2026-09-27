package entity;

import entity.enums.Genre;
import entity.enums.Language;

import java.time.LocalDate;

public class Movie {
    private final long id;
    private final String title;
    private final Language language;
    private final Genre genre;
    private final LocalDate releaseDate;
    public Movie(long id, String title, Language language, Genre genre, LocalDate
                 releaseDate){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Language getLanguage() {
        return language;
    }

    public Genre getGenre() {
        return genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
