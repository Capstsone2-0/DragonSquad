package com.pluralsight.AccountingLedger.data.mysql;

import com.pluralsight.AccountingLedger.models.Transactions;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlTransactionDao extends MySqlDaoBase implements com.pluralsight.AccountingLedger.data.mysql.TransactionDao {


    public MySqlTransactionDao(DataSource dataSource)
    {
        super(dataSource);
    }
    @Override
    public List<Transactions> getAllTransactions()
    {
        List<Transactions> res = new ArrayList<>();
        String sql = "SELECT * FROM transactions";
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                Transactions t = mapRow(row);
                res.add(t);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public List<Transactions> getDeposit() {
        List<Transactions> res = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE amount>0;";
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                Transactions t = mapRow(row);
                res.add(t);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public List<Transactions> getPayment() {
        List<Transactions> res = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE amount<0;";
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                Transactions t = mapRow(row);
                res.add(t);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public List<Transactions> getMonthToDate() {

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        List <Transactions> allTransactions = getAllTransactions();
        List<Transactions> res = new ArrayList<>();
        for(Transactions t: allTransactions)
        {
            LocalDate transactionDate = t.getDate();

            if (transactionDate.getYear() == currentYear && transactionDate.getMonthValue() == currentMonth) {
                res.add(t);
            }
        }

        return res;
    }

    @Override
    public List<Transactions> getPreviousMonth() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        int prevMonth = (currentMonth == 1) ? 12 : currentMonth - 1; //if month is 1 then previous month will be 12 of previous year
        int prevYear = (currentMonth == 1) ? currentYear - 1 : currentYear; //if month is 1 then previous month will be from previous year


        List <Transactions> allTransactions = getAllTransactions();
        List<Transactions> res = new ArrayList<>();
        for(Transactions t: allTransactions)
        {
            LocalDate transactionDate = t.getDate();

            if (transactionDate.getYear() == prevYear && transactionDate.getMonthValue() == prevMonth) { //check year and month and add in list
                res.add(t);
            }
        }

        return res;
    }

    @Override
    public List<Transactions> getYearToDate() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        LocalDate today = LocalDate.now();
        LocalDate firstDayOfTheYear = LocalDate.of(currentYear, 1, 1);

        List <Transactions> allTransactions = getAllTransactions();
        List<Transactions> res = new ArrayList<>();
        for(Transactions t: allTransactions)
        {
            LocalDate transactionDate = t.getDate();

            if ((transactionDate.getYear() == currentYear) && transactionDate.isEqual(firstDayOfTheYear) || transactionDate.isAfter(firstDayOfTheYear) && transactionDate.isBefore(today) || transactionDate.isEqual(today)) {
                res.add(t);
            }

        }

        return res;
    }

    @Override
    public List<Transactions> getPreviousYear() {
        LocalDate currentDate = LocalDate.now();

        int lastYear = currentDate.getYear()-1; //getting only year
        List <Transactions> allTransactions = getAllTransactions();
        List<Transactions> res = new ArrayList<>();
        for(Transactions t: allTransactions)
        {
            LocalDate transactionDate = t.getDate();
            if (transactionDate.getYear() == lastYear) { //comparing only year
                res.add(t);
            }
        }

        return res;
    }


    private Transactions mapRow(ResultSet row) throws SQLException
    {
        int id = row.getInt("id");
        java.sql.Date sqlDate = row.getDate("date");
        LocalDate date = (sqlDate != null) ? sqlDate.toLocalDate() : null;
        String time = row.getString("time");
        LocalTime t = LocalTime.parse(time);
        String description = row.getString("description");
        String vendor = row.getString("vendor");
        double amount = row.getDouble("amount");

         return new Transactions(id, date, t, description, vendor, amount);
    }
}
