package app;

import service.FacebookService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FacebookService facebook = new FacebookService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to Facebook Graph App!");

        while (running) {
            printMenu();
            System.out.print("Enter choice: ");
            String input = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> createUser(scanner, facebook);
                case 2 -> createPost(scanner, facebook);
                case 3 -> likePost(scanner, facebook);
                case 4 -> commentOnPost(scanner, facebook);
                case 5 -> facebook.displayAllUsers();
                case 6 -> facebook.displayPosts();
                case 7 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("========= FACEBOOK =========");
        System.out.println("1. Create User");
        System.out.println("2. Create Post");
        System.out.println("3. Like Post");
        System.out.println("4. Comment");
        System.out.println("5. Display All Users");
        System.out.println("6. Display Posts");
        System.out.println("7. Exit");
        System.out.println("============================");
    }

    private static void createUser(Scanner scanner, FacebookService facebook) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        if (name.isEmpty() || email.isEmpty()) {
            System.out.println("Name and email cannot be empty.");
            return;
        }

        facebook.createUser(name, email);
    }

    private static void createPost(Scanner scanner, FacebookService facebook) {
        System.out.print("Enter User ID: ");
        int userId = readInt(scanner);
        if (userId == -1) {
            return;
        }

        System.out.print("Enter caption: ");
        String caption = scanner.nextLine().trim();
        if (caption.isEmpty()) {
            System.out.println("Caption cannot be empty.");
            return;
        }

        facebook.createPost(userId, caption);
    }

    private static void likePost(Scanner scanner, FacebookService facebook) {
        System.out.print("Enter Post ID: ");
        int postId = readInt(scanner);
        if (postId == -1) {
            return;
        }

        System.out.print("Enter User ID (who is liking): ");
        int userId = readInt(scanner);
        if (userId == -1) {
            return;
        }

        facebook.likePost(postId, userId);
    }

    private static void commentOnPost(Scanner scanner, FacebookService facebook) {
        System.out.print("Enter Post ID: ");
        int postId = readInt(scanner);
        if (postId == -1) {
            return;
        }

        System.out.print("Enter User ID (who is commenting): ");
        int userId = readInt(scanner);
        if (userId == -1) {
            return;
        }

        System.out.print("Enter comment: ");
        String text = scanner.nextLine().trim();
        if (text.isEmpty()) {
            System.out.println("Comment cannot be empty.");
            return;
        }

        facebook.commentOnPost(postId, userId, text);
    }

    private static int readInt(Scanner scanner) {
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return -1;
        }
    }
}
