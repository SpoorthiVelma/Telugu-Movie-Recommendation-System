package service;

import model.Movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MovieService {

    public List<Movie> loadMovies(String filePath) {

        List<Movie> movies = new ArrayList<>();

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(filePath));

            String line;

            br.readLine();

            int id = 1;

            while ((line = br.readLine()) != null) {

            String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            String title = data[1];

            String genre = data[4]
                    .replace("\"", "")
                    .trim();

            double rating =
                    Double.parseDouble(data[7]);

            String language = "Telugu";

            Movie movie =
                    new Movie(
                            id++,
                            title,
                            genre,
                            language,
                            rating
                    );

            movies.add(movie);
            }

            br.close();

        } catch (Exception e) {

            System.out.println(
                    "Error loading dataset: "
                            + e.getMessage());
        }

        return movies;
    }

    public void displayMovies(List<Movie> movies) {

        for (Movie movie : movies) {

            System.out.println(movie);
        }
    }

    public void searchMovie(
            List<Movie> movies,
            String keyword) {

        for (Movie movie : movies) {

            if (movie.getTitle()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                System.out.println(movie);
            }
        }
    }

    public void filterByGenre(
            List<Movie> movies,
            String genre) {

        for (Movie movie : movies) {

            if (movie.getGenre()
                    .toLowerCase()
                    .contains(genre.toLowerCase())) {

                System.out.println(movie);
            }
        }
    }

    public void recommendMovies(
            List<Movie> movies,
            String movieName) {

        String genre = "";

        for (Movie movie : movies) {

            if (movie.getTitle()
                    .equalsIgnoreCase(movieName)) {

                genre = movie.getGenre();
                break;
            }
        }

        System.out.println(
                "Recommended Movies:");

        for (Movie movie : movies) {

            if (movie.getGenre()
                    .equalsIgnoreCase(genre)

                    && !movie.getTitle()
                    .equalsIgnoreCase(movieName)) {

                System.out.println(movie);
            }
        }
    }
}