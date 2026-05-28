package model;

public class Movie {

    private int id;
    private String title;
    private String genre;
    private String language;
    private double rating;

    public Movie(int id, String title,
                 String genre,
                 String language,
                 double rating) {

        this.id = id;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {

        return id + " | " +
                title + " | " +
                genre + " | " +
                language + " | " +
                rating;
    }
}