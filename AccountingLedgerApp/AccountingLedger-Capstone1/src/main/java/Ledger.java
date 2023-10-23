import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
              float amount = Float.parseFloat(fields[4]);
              Transaction transaction = new Transaction(date, time, description, vendor, (double) amount);
              addTransaction(transaction);


          }

      } catch (IOException e) {
          System.out.println("Error");

      } ;
    }
}
