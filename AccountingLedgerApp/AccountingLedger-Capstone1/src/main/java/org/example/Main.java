package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // Ledger ledger = new Ledger();
        System.out.println(" HOME ");
        System.out.println("-------------------------");

        while (true) {
            System.out.println("Select option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment(Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.next().toUpperCase();

            switch (choice) {
                case "D":
                    System.out.println("\nEnter deposit information:");
                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = scanner.next();
                    System.out.print("Time (HH:MM:SS): ");
                    String time = scanner.next();
                    System.out.print("Description: ");
                    String description = scanner.next();
                    System.out.print("Vendor: ");
                    String vendor = scanner.next();
                    System.out.print("Amount: ");
                    double amount = scanner.nextDouble();
                    // add deposit
                    System.out.println("\nDeposit added!");
                    break;
                case "L":
                    System.out.println("\nLedger Options:");
                    System.out.println("A) All");
                    System.out.println("D) Deposits");
                    System.out.println("P) Payments");
                    System.out.println("R) Reports");
                    System.out.println("0) Back");
                    System.out.println("H) Home");

                    System.out.print("Please enter your choice: ");
                    String ledgerChoice = scanner.next().toUpperCase();
                break;

            }
        }
    }
}