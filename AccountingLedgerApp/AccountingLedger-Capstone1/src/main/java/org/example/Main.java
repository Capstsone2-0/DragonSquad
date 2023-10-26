package org.example;

import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Ledger ledger = new Ledger();
       // ledger.readTransactionFile(ledger.fileName);


        System.out.println("""
                 _____________________________________________
                | ✨ Welcome To My Accounting Ledger App! ✨|
                 ---------------------------------------------
                """);
//        System.out.println("""
//                  +++++++++
//                | 🏦 HOME 🏦|
//                  +++++++++
//                """);
        System.out.println("""
                ==================================
                     🏦     HOME MENU   🏦
                ==================================
                Choose by letter:
                """);

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
                case "P":
                    ledger.makePayment();
                    break;
                case "L":
                    ledger.LedgerScreen();
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

//    private static void showLedgersScreen(Scanner scanner) {
//        System.out.println("Ledger Options:");
//        System.out.println("A) All");
//        System.out.println("D) Deposits");
//        System.out.println("P) Payments");
//        System.out.println("R) org.example.Reports");
//        System.out.println("0) Back");
//        System.out.println("H) Home");
//
//        System.out.print("Please enter your choice: ");
//        String ledgerChoice = scanner.next().toUpperCase();
//
//        try {
//            System.out.println("Ledger: ");
//            System.out.println("Please enter your selection: ");
//
//           // String ledgerchoice = scanner.nextLine();
//
//            switch (ledgerChoice.toUpperCase()) {
//                case "A":
//                    displayAllTransactions();
//                    break;
//                case "D":
//                    displayDeposits();
//                    break;
//                case "P":
//                    displayPayment();
//                    break;
//                case "R":
//                    reports.showReports(transactions);
//                    break;
//                case "H":
//                    return;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//
//        } catch (Exception e) {
//            System.out.println("There was an error");
//        }
//
//    }
}