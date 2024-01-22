package com.pluralsight.AccountingLedger.data;

import com.pluralsight.AccountingLedger.models.Transactions;

import java.util.List;

public interface TransactionDao {
    public List<Transactions> getAllTransactions();

    List<Transactions> getDeposit();

    List<Transactions> getPayment();

    List<Transactions> getMonthToDate();

    List<Transactions> getPreviousMonth();

    List<Transactions> getYearToDate();

    List<Transactions> getPreviousYear();

    Transactions create(Transactions t);

    Transactions createPayment(Transactions t);
}