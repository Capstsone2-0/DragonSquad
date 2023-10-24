package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

         Scanner scanner = new Scanner(System.in);
         Ledger ledger = new Ledger();


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

                    ledger.addDeposit();


                    System.out.println("\nDeposit added!");
                    break;
                case  "P":
                    ledger.makePayment();
                case "L":
                    System.out.println("\norg.example.Ledger Options:");
                    System.out.println("A) All");
                    System.out.println("D) Deposits");
                    System.out.println("P) Payments");
                    System.out.println("R) org.example.Reports");
                    System.out.println("0) Back");
                    System.out.println("H) Home");

                    System.out.print("Please enter your choice: ");
                    String ledgerChoice = scanner.next().toUpperCase();
                break;
                case "X":
                    System.out.println("Exiting the application");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}