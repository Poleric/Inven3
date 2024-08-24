package com.lavacorp;

import com.lavacorp.db.Database;
import com.lavacorp.db.dao.UserDao;
import com.lavacorp.models.User;
import com.lavacorp.models.generic.Login;

import java.util.Scanner;

public class LoginUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void DisplayUI() {
        while (true) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== Login System =====");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User loggedInUser = Login.login(username, password);
        if (loggedInUser != null) {
            System.out.println("Login successful!");
            if (loggedInUser.isAdmin()) {
                System.out.println("Logged in as admin user.");
                displayAdminMenu(loggedInUser);
            } else {
                System.out.println("Welcome, " + loggedInUser.getName() + "!");
            }
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    private static void register() {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        System.out.print("Is admin? (true/false): ");
        boolean isAdmin = Boolean.parseBoolean(scanner.nextLine());

        Login.register(username, password, isAdmin);
        System.out.println("User registered successfully!");
    }

    private static void displayAdminMenu(User adminUser) {
        while (true) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. List all users");
            System.out.println("2. Logout");
            System.out.print("Enter your choice: ");

            int choice = getChoice();
            switch (choice) {
                case 1:
                    listAllUsers();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void listAllUsers() {
        System.out.println("\nAll users:");

        UserDao dao = Database.getJdbi().onDemand(UserDao.class);

        for (User user : dao.selectAll()) {
            System.out.println("Username: " + user.getName() + ", Admin: " + user.isAdmin());
        }
    }


}