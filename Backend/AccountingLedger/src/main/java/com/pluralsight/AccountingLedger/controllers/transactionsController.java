package com.pluralsight.AccountingLedger.controllers;

import com.pluralsight.AccountingLedger.data.mysql.TransactionDao;
import com.pluralsight.AccountingLedger.models.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
@PermitAll
public class transactionsController {
    private TransactionDao transactionDao;

    @Autowired
    public transactionsController(TransactionDao transactionDao)
    {
        this.transactionDao = transactionDao;
    }

    @GetMapping("all")
    public List<Transactions> getAll()
    {
        return transactionDao.getAllTransactions();
    }
    @GetMapping("deposit")
    public List<Transactions> getDeposit()
    {
        return transactionDao.getDeposit();
    }
    @GetMapping("payment")
    public List<Transactions> getPayment()
    {
        return transactionDao.getPayment();
    }

    @GetMapping("mtd")
            public List<Transactions> getMonthToDate()
    {
        return transactionDao.getMonthToDate();
    }

    @GetMapping("pm")
    public List<Transactions> getPreviousMonth()
    {
        return transactionDao.getPreviousMonth();
    }

    @GetMapping("ytd")
    public List<Transactions> getYearToDate()
    {
        return transactionDao.getYearToDate();
    }

    @GetMapping("py")
    public List<Transactions> getPreviousYear()
    {
        return transactionDao.getPreviousYear();
    }

}
