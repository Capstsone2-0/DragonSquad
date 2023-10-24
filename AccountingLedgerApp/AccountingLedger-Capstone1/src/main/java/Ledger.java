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

    public Ledger() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    String fileName = "transactions.csv";

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
                    filewriter.close();

                } catch (IOException e) {
                    System.out.println("file writer unsuccessful");
                }
                break;

            } catch (DateTimeException e) {
                System.out.println("date and time error");
            }
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


}