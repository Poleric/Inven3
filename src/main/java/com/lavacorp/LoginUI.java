package com.lavacorp;

import com.lavacorp.db.Database;
import com.lavacorp.db.dao.UserDao;
import com.lavacorp.models.User;
import com.lavacorp.models.UserType;

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
                    System.out.println("Exiting the program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("You are enter Invalid Number, Please try again.");
            }
        }
    }
    //login menu
    private static void displayMenu() {
        System.out.println("\n================");
        System.out.println("+ Login System +");
        System.out.println("================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("\nEnter your choice: ");
    }

    //get input and turn to int
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
            if (loggedInUser.getUserType() == UserType.ADMIN) {
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
        UserType userType = Boolean.parseBoolean(scanner.nextLine()) ? UserType.ADMIN : UserType.STAFF;

        Login.register(username, password, userType);
        System.out.println("User registered successfully!");
    }

    private static void displayAdminMenu(User adminUser) {
        while (true) {
            System.out.println("\n================");
            System.out.println("+ Admin Menu +");
            System.out.println("================");
            System.out.println("1. List all users");
            System.out.println("2. Logout");
            System.out.print("\nEnter your choice: ");

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
            System.out.println("Username: " + user.getName() + ", Admin: " + (user.getUserType() == UserType.ADMIN));
        }
    }
}