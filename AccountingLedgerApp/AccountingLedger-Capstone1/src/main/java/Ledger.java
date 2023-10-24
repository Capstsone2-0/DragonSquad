import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ledger {


    private List<Transaction> transactions;

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
                Transaction transaction = new Transaction(date, time, description, vendor, (double) amount);
                addTransaction(transaction);

            }

        } catch (IOException e) {
            System.out.println("Error");

        }
        ;

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
                    FileWriter filewriter = new FileWriter("transactions.csv, true");
                    filewriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");
                    filewriter.close();
                } catch (IOException e) {
                    System.out.println("file writer unsuccessful");
                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}