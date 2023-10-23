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

    public void readTransactionFile(String filename) {

    }
}
