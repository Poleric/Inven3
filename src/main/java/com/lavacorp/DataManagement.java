package com.lavacorp;

import com.lavacorp.db.Database;
import com.lavacorp.db.dao.UserDao;
import com.lavacorp.models.User;
import com.lavacorp.models.UserType;

import java.util.Scanner;

public class DataManagement {
    private static final Scanner scanner =  new Scanner(System.in);

    private static void addData(String item) {

    }

    private static void removeData(String item) {

    }

    private static void updateData(String item) {

    }

    private static void viewData() {
        System.out.print("something ig");


    }

    private static void searchData(String item) {
        System.out.print("Something will come out here");
    }

    private static void filterData() {

    }

    private static void searchData() {
        System.out.print("Search Item: ");
        String item = scanner.nextLine();
        searchData(item);

    }
}
