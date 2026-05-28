import model.Movie;
import service.MovieService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MovieService movieService =
                new MovieService();

        List<Movie> movies =
                movieService.loadMovies(
                        "TeluguMovies_dataset.csv");

        Scanner sc = new Scanner(System.in);

        System.out.println(
                "===== MOVIE RECOMMENDATION SYSTEM =====");

        System.out.println("1. Display Movies");
        System.out.println("2. Search Movie");
        System.out.println("3. Filter By Genre");
        System.out.println("4. Recommend Movies");

        System.out.print("Enter Choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1:

                movieService.displayMovies(movies);
                break;

            case 2:

                System.out.print(
                        "Enter movie name: ");

                String keyword =
                        sc.nextLine();

                movieService.searchMovie(
                        movies,
                        keyword);

                break;

            case 3:

                System.out.print(
                        "Enter genre: ");

                String genre =
                        sc.nextLine();

                movieService.filterByGenre(
                        movies,
                        genre);

                break;

            case 4:

                System.out.print(
                        "Enter movie name: ");

                String movieName =
                        sc.nextLine();

                movieService.recommendMovies(
                        movies,
                        movieName);

                break;

            default:

                System.out.println(
                        "Invalid Choice");
        }
        sc.close();
    }
}