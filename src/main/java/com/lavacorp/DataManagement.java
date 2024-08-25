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

    public static void removeSupplier() {
        System.out.print("Enter supplier's name: ");
        String name = scanner.nextLine();

        SupplierDao dao = Database.getJdbi().onDemand(SupplierDao.class);
        dao.deleteByName(name);

        System.out.println("\nSupplier deleted successfully.\n");
    }

    public static void updateSupplier() {
        SupplierDao dao = Database.getJdbi().onDemand(SupplierDao.class);
        System.out.print("Enter supplier's ID: ");
        int id = scanner.nextInt();
        Supplier supplier = dao.selectById(id);

        int choice;
        int update;
        do {
            update = 0;
            System.out.println("Selected supplier: " + supplier.getName());
            System.out.println("1. Update Name");
            System.out.println("2. Update Address");
            System.out.println("3. Update Phone Number");
            System.out.println("4. Update Email");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Current Supplier's Name: " + supplier.getName());
                    System.out.print("Enter new Supplier's Name: ");
                    supplier.setName(scanner.nextLine());
                    System.out.println();
                    update++;
                    break;
                case 2:
                    System.out.println("Current Supplier's Address: " + supplier.getAddress());
                    System.out.print("Enter new Supplier's Address: ");
                    supplier.setAddress(scanner.nextLine());
                    System.out.println();
                    update++;
                    break;
                case 3:
                    System.out.println("Current Supplier's Phone Number: " + supplier.getPhoneNumber());
                    System.out.print("Enter new Supplier's Phone Number: ");
                    supplier.setPhoneNumber(scanner.nextLine());
                    System.out.println();
                    update++;
                    break;
                case 4:
                    System.out.println("Current Supplier's Email: " + supplier.getEmail());
                    System.out.print("Enter new Supplier's Email: ");
                    supplier.setEmail(scanner.nextLine());
                    System.out.println();
                    update++;
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        } while (choice != 5);

        if (update > 0) {
            dao.update(supplier);
            System.out.println("\nSupplier updated successfully.\n");
        }
        else {
            System.out.println("Exiting...\n");
        }
    }

    public static void viewSupplier() {
        SupplierDao dao = Database.getJdbi().onDemand(SupplierDao.class);
        System.out.printf("%-13s %-18s %-32s %-17s %-25s\n",
                "Supplier ID", "Supplier Name", "Address", "Phone Number", "Email");
        System.out.println("-".repeat(106));

        for (Supplier supplier : dao.selectAll()) {
            System.out.printf("%-13d %-18s %-32s %-17s %-25s\n",
                    supplier.getId(), supplier.getName(), supplier.getAddress(),
                    supplier.getPhoneNumber(), supplier.getEmail());
        }
    }

    public static void searchSupplier() {
        SupplierDao dao = Database.getJdbi().onDemand(SupplierDao.class);
        System.out.print("Search supplier name: ");
        String name = scanner.nextLine();

        System.out.printf("%-13s %-18s %-32s %-17s %-25s\n",
                "Supplier ID", "Supplier Name", "Address", "Phone Number", "Email");
        System.out.println("-".repeat(106));

        for (Supplier supplier : dao.selectAll()) {
            dao.selectByName(name);
            System.out.printf("%-13d %-18s %-32s %-17s %-25s\n",
                    supplier.getId(), supplier.getName(), supplier.getAddress(),
                    supplier.getPhoneNumber(), supplier.getEmail());
        }
    }

    public static void filterSupplier() {
        System.out.print("idk how to do this?");
    }
}
