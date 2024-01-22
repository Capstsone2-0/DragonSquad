package com.pluralsight.AccountingLedger.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transactions {

    private final int id;
    private final LocalDate date;

    public int getId() {
        return id;
    }

    private final LocalTime time;
    private final String description;
    private final String vendor;
    private final double amount;
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
    public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Transactions(int id,LocalDate date, LocalTime time, String description, String vendor, Double amount) {
        this.id = id;
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


    class Deposit extends Transactions {
        public Deposit(int id, LocalDate date, LocalTime time, String description, String vendor, double amount) {
            super(id, date, time, description, vendor, amount);
        }
    }
    class Payment extends Transactions {
        public Payment(int id, LocalDate date, LocalTime time, String description, String vendor, double amount)  {
            super(id, date, time, description, vendor, (double) amount);
        }
    }
}
