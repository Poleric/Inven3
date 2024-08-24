package com.lavacorp;

import com.lavacorp.db.Database;
import com.lavacorp.db.dao.SupplierDao;
import com.lavacorp.models.Supplier;

import java.util.Scanner;

public class DataManagement {
    private static final Scanner scanner =  new Scanner(System.in);

    public static void addSupplier() {
        Supplier supplier = new Supplier();

        System.out.print("Enter supplier's name: ");
        supplier.setName(scanner.nextLine());
        System.out.print("Enter supplier's address: ");
        supplier.setAddress(scanner.nextLine());
        System.out.print("Enter supplier's phone number: ");
        supplier.setPhoneNumber(scanner.nextLine());
        System.out.print("Enter supplier's email: ");
        supplier.setEmail(scanner.nextLine());

        SupplierDao dao = Database.getJdbi().onDemand(SupplierDao.class);
        dao.insert(supplier);

        System.out.println("\nSupplier added successfully.\n");
    }

    private static void removeSupplier() {
        System.out.print("Enter supplier's name: ");
        String name = scanner.nextLine();

        SupplierDao dao = Database.getJdbi().onDemand(SupplierDao.class);
        dao.delete(name);

        System.out.println("\nSupplier deleted successfully.\n");
    }

    private static void updateSupplier() {

    }

    private static void viewData() {

    }

    private static void searchData() {
        System.out.print("Something will come out here");
    }

    private static void filterData() {
        System.out.print("idk how to do this?");
    }
}
