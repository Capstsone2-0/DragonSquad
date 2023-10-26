package org.example;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;

public class Ledger {
    private final List<Transaction> transactions;
    String fileName = "transactions.csv";
    public Ledger() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }


    public void readTransactionFile(String filename) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                LocalDate date = LocalDate.parse(fields[0], dateFormatter);
                LocalTime time = LocalTime.parse(fields[1], timeFormatter);
                String description = fields[2];
                String vendor = fields[3];
                Double amount = Double.parseDouble(fields[4]);
                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                addTransaction(transaction);

            }

        } catch (IOException e) {
            System.out.println("Error");

        }

    }

    //Make payment using while loop, transfer all attributes
    Scanner scanner = new Scanner(System.in);

    public void makePayment() {

        while (true) {
            try {
                System.out.println("enter payment using  yyyy-MM-dd :");
                LocalDate date = LocalDate.parse(scanner.nextLine());

                System.out.print("Enter time HH:mm:ss : ");
                LocalTime time = LocalTime.parse(scanner.nextLine());

                System.out.print("Description:");
                String description = scanner.nextLine();

                System.out.println("Vendor: ");
                String vendor = scanner.nextLine();

                System.out.println("Enter Amount: ");
                Double amount = scanner.nextDouble();
                scanner.nextLine();
                Transaction payment = new Transaction(date, time, description, vendor, amount);
                addTransaction(payment);
                System.out.println("Added!");


                try {
                    FileWriter filewriter = new FileWriter("transactions.csv", true);
                    filewriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");
                    filewriter.flush();
                    filewriter.close();

                } catch (IOException e) {
                    System.out.println("file writer unsuccessful");
                }
                break;

            } catch (DateTimeException e) {
                System.out.println("date and time error");
            }
            break;
        }
    }

    public void addDeposit() {
        while (true) {

            try {
                System.out.println(" enter deposit date yyyy-MM-dd : ");
                LocalDate date = LocalDate.parse((scanner.nextLine()));
                System.out.println("Enter time HH:mm:ss: ");
                LocalTime time = LocalTime.parse(scanner.nextLine());
                System.out.println("Enter description: ");
                String description = scanner.nextLine();
                System.out.println("Enter vendor: ");
                String vendor = scanner.nextLine();
                System.out.println("Enter amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                Transaction deposit = new Transaction(date, time, description, vendor, amount);
                addTransaction(deposit);
                System.out.println("Deposit added");
                try {
                    FileWriter fileWrite = new FileWriter("transactions.csv", true);
                    fileWrite.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");
                    fileWrite.flush();
                    fileWrite.close();
                } catch (IOException e) {
                    System.out.println("Failed to write to file. ");

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
      // displayed, transactions, Deposits, payment, Reports(once completed), search ny vendor
    public void displayAllTransactions() {
        System.out.println("These are your Transactions: ");
        if (transactions.size() == 0) {
            System.out.println("no transactions");
        } else {
            for (Transaction transaction : transactions) {
                System.out.printf("%f\n", transaction.getAmount());
                System.out.printf("%f\n", transaction.getDate());
                System.out.printf("%f\n", transaction.getTime());
                System.out.printf("%f\n", transaction.getDescription());
                System.out.printf("%f\n", transaction.getVendor());
            }
        }


    }
    public void displayDeposits() {
        System.out.println("Here are your deposits: ");
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction.getDate() + " " + transaction.getDescription() + " " + transaction.getTime() + " " + transaction.getVendor() + " $" + transaction.getAmount());
            }
        }
    }
    public void displayPayment() {
        System.out.println("Here are your payments: ");
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction.getDate() + " " + transaction.getDescription() + " " + transaction.getTime() + " " + transaction.getVendor() + " $" + transaction.getAmount());
            }
        }

    }

    public void determineType() {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0.0f) {

                transaction.setType("Deposit");

            } else {
                transaction.setType("Payment");
            }
        }
    }
    Reports reports = new Reports();

    public void LedgerScreen() {
        try {
        System.out.println("Ledger Options:");
        System.out.println("A) All");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) org.example.Reports");
        System.out.println("0) Back");
        System.out.println("H) Home");

        System.out.print("Please enter your choice: ");
            System.out.println("Please enter your selection: ");

            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "A":
                    displayAllTransactions();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayment();
                    break;
                case "R":
                    reports.showReports(transactions);
                    break;
                case "H":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } catch (Exception e) {
            System.out.println("There was an error");
        }
    }



}