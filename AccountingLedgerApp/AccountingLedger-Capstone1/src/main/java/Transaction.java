import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;
    private String type;

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, Double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;



        if (amount > 0) {
            this.type = "Deposit";
        }else{
            this.type = "payment";
        }
    }


    @Override
    public String toString() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }


    class Deposit extends Transaction {
        public Deposit(LocalDate date, LocalTime time, String description, String vendor, double amount) {
            super(date, time, description, vendor, amount);
        }
    }
    class Payment extends Transaction {
        public Payment(LocalDate date, LocalTime time, String description, String vendor, float amount)  {
            super(date, time, description, vendor, (double) amount);
        }
    }
}
