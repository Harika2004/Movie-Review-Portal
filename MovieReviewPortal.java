/*import java.util.*;

public class MovieReviewPortal {
    private static Map<String, Double> movieRatings = new HashMap<>();
    private static Map<String, List<String>> movieReviews = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Movie Review Portal!");
            System.out.println("1. Add a movie rating");
            System.out.println("2. Add a movie review");
            System.out.println("3. Get movie rating");
            System.out.println("4. Get movie reviews");
            System.out.println("5. Exit");
            System.out.print("Please enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addMovieRating(scanner);
                    break;
                case 2:
                    addMovieReview(scanner);
                    break;
                case 3:
                    getMovieRating(scanner);
                    break;
                case 4:
                    getMovieReviews(scanner);
                    break;
                case 5:
                    System.out.println("Thanks for using the Movie Review Portal!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void addMovieRating(Scanner scanner) {
        System.out.print("Enter movie name: ");
        String movieName = scanner.next();
        System.out.print("Enter movie rating (out of 10): ");
        double rating = scanner.nextDouble();
        movieRatings.put(movieName, rating);
        System.out.println("Movie rating added successfully!");
    }

    private static void addMovieReview(Scanner scanner) {
        System.out.print("Enter movie name: ");
        String movieName = scanner.next();
        scanner.nextLine();
        System.out.print("Enter review: ");
        String review = scanner.nextLine();
        if (movieReviews.containsKey(movieName)) {
            movieReviews.get(movieName).add(review);
        } else {
            List<String> reviews = new ArrayList<>();
            reviews.add(review);
            movieReviews.put(movieName, reviews);
        }
        System.out.println("Movie review added successfully!");
    }

    private static void getMovieRating(Scanner scanner) {
        System.out.print("Enter movie name: ");
        String movieName = scanner.next();
        if (movieRatings.containsKey(movieName)) {
            System.out.println("Movie rating for " + movieName + " is " + movieRatings.get(movieName));
        } else {
            System.out.println("Movie not found.");
        }
    }

    private static void getMovieReviews(Scanner scanner) {
        System.out.print("Enter movie name: ");
        String movieName = scanner.next();
        if (movieReviews.containsKey(movieName)) {
            List<String> reviews = movieReviews.get(movieName);
            System.out.println("Movie reviews for " + movieName + ":");
            for (String review : reviews) {
                System.out.println(review);
            }
        } else {
            System.out.println("Movie not found.");
        }
    }
}*/
import java.io.*;
import java.util.Scanner;

public class MovieReviewPortal {

    private static final String FILE_PATH = "reviews.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Movie Review Portal");
            System.out.println("1. Add a review");
            System.out.println("2. View all reviews");
            System.out.println("3. Search for reviews by movie title");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addReview(scanner);
                    break;
                case 2:
                    viewAllReviews();
                    break;
                case 3:
                    searchReviewsByTitle(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addReview(Scanner scanner) {
        System.out.print("Enter the movie title: ");
        String title = scanner.nextLine();

        System.out.print("Enter your review: ");
        String review = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(title + ":" + review);
            writer.newLine();
            System.out.println("Review added successfully!");
        } catch (IOException e) {
            System.out.println("Failed to add review: " + e.getMessage());
        }
    }

    private static void viewAllReviews() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String title = parts[0];
                String review = parts[1];
                System.out.println("Title: " + title);
                System.out.println("Review: " + review);
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Failed to view reviews: " + e.getMessage());
        }
    }

    private static void searchReviewsByTitle(Scanner scanner) {
        System.out.print("Enter the movie title to search: ");
        String searchTitle = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String title = parts[0];
                String review = parts[1];
                if (title.equalsIgnoreCase(searchTitle)) {
                    System.out.println("Title: " + title);
                    System.out.println("Review: " + review);
                    System.out.println();
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No reviews found for the given movie title.");
            }
        } catch (IOException e) {
            System.out.println("Failed to search reviews: " + e.getMessage());
        }
    }
}
