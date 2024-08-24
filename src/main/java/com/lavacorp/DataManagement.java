package com.lavacorp;

import com.lavacorp.db.Database;
import com.lavacorp.db.dao.UserDao;
import com.lavacorp.models.User;
import com.lavacorp.models.UserType;

import java.util.Scanner;

public class DataManagement {
    private static final Scanner scanner =  new Scanner(System.in);

    private static void addData(String item) {

        if (true) {
            System.out.println(item + " added.\n");
        }
    }

    private static void removeData(String item) {

        if (true) {
            System.out.println(item + " removed.\n");
        }
    }

    private static void updateData(String item) {

        if (true) {
            System.out.println(item + " updated.\n");
        }
    }

    private static void viewData() {
        System.out.print("something ig");


    }

    private static void searchData(String item) {
        System.out.print("Something will come out here");
    }

    private static void filterData() {
        System.out.print("idk how to do this?");
    }

    // UIs

    private static void addData() {
        System.out.print("Add item: ");
        String item = scanner.nextLine();
        addData(item);
    }

    private static void removeData() {
        System.out.print("Remove item: ");
        String item = scanner.nextLine();
        removeData(item);
    }

    private static void updateData() {
        System.out.print("Update item: ");
        String item = scanner.nextLine();
        updateData(item);
    }

    private static void searchData() {
        System.out.print("Search Item: ");
        String item = scanner.nextLine();
        searchData(item);
    }
}
