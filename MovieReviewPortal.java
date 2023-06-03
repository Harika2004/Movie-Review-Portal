import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Movie {
    private String title;
    private String director;
    private int releaseYear;
    private double rating;
    private List<String> reviews;

    public Movie(String title, String director, int releaseYear, double rating) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.reviews = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void addReview(String review) {
        reviews.add(review);
    }

    @Override
    public String toString() {
        return "Movie [title=" + title + ", director=" + director + ", releaseYear=" + releaseYear + ", rating=" + rating
                + "]";
    }
}

class MovieReviewPortal {
    private Map<String, Movie> movies;

    public MovieReviewPortal() {
        movies = new HashMap<>();
    }

    public void addMovie(String title, String director, int releaseYear, double rating) {
        Movie movie = new Movie(title, director, releaseYear, rating);
        movies.put(title, movie);
    }

    public void addReview(String title, String review) {
        Movie movie = movies.get(title);
        if (movie != null) {
            movie.addReview(review);
        }
    }

    public Movie getMovie(String title) {
        return movies.get(title);
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies.values());
    }
}

public class Main {
    public static void main(String[] args) {
        MovieReviewPortal portal = new MovieReviewPortal();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("1. Add Movie");
            System.out.println("2. Add Review");
            System.out.println("3. View Movie Reviews");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter movie title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter director: ");
                    String director = scanner.nextLine();
                    System.out.print("Enter release year: ");
                    int releaseYear = scanner.nextInt();
                    System.out.print("Enter rating: ");
                    double rating = scanner.nextDouble();
                    portal.addMovie(title, director, releaseYear, rating);
                    System.out.println("Movie added successfully!");
                    break;

                case 2:
                    System.out.print("Enter movie title: ");
                    String reviewTitle = scanner.nextLine();
                    System.out.print("Enter your review: ");
                    String review = scanner.nextLine();
                    portal.addReview(reviewTitle, review);
                    System.out.println("Review added successfully!");
                    break;

                case 3:
                    System.out.print("Enter movie title: ");
                    String movieTitle = scanner.nextLine();
                    Movie movie = portal.getMovie(movieTitle);
                    if (movie != null) {
                        System.out.println("Movie: " + movie);
                        System.out.println("Reviews:");
                        for (String r : movie.getReviews()) {
                            System.out.println("- " + r);
                        }
                    } else {
                        System.out.println("Movie not found!");
                    }
                    break;

                case 4:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }
}
