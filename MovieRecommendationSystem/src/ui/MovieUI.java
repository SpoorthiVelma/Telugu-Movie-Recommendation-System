package ui;

import model.Movie;
import service.MovieService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MovieUI extends JFrame {

    private JTextField searchField;
    private JTextArea resultArea;

    private MovieService movieService;
    private List<Movie> movies;

    public MovieUI(List<Movie> movies,
                   MovieService movieService) {

        this.movies = movies;
        this.movieService = movieService;

        setTitle("Movie Recommendation System");

        setSize(700, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        JLabel label =
                new JLabel("Enter Movie:");

        searchField = new JTextField(20);

        JButton recommendButton =
                new JButton("Recommend");

        topPanel.add(label);
        topPanel.add(searchField);
        topPanel.add(recommendButton);

        add(topPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();

        JScrollPane scrollPane =
                new JScrollPane(resultArea);

        add(scrollPane, BorderLayout.CENTER);

        recommendButton.addActionListener(e -> {

            String movieName =
                    searchField.getText();

            showRecommendations(movieName);
        });

        setVisible(true);
    }

    private void showRecommendations(
            String movieName) {

        resultArea.setText("");

        String genre = "";

        for (Movie movie : movies) {

            if (movie.getTitle()
                    .equalsIgnoreCase(movieName)) {

                genre = movie.getGenre();
                break;
            }
        }

        resultArea.append(
                "Recommended Movies:\n\n");

        for (Movie movie : movies) {

            if (movie.getGenre()
                    .equalsIgnoreCase(genre)

                    && !movie.getTitle()
                    .equalsIgnoreCase(movieName)) {

                resultArea.append(
                        movie.toString() + "\n");
            }
        }
    }
}