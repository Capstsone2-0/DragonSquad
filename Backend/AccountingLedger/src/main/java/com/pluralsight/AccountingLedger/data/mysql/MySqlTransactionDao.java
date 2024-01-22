package com.pluralsight.AccountingLedger.data.mysql;

import com.pluralsight.AccountingLedger.models.Transactions;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlTransactionDao extends MySqlDaoBase implements com.pluralsight.AccountingLedger.data.TransactionDao {


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

    @Override
    public Transactions create(Transactions t) {

        String sql = "INSERT INTO transactions(date, time, vendor, description, amount) " +
                " VALUES (?, ?, ?, ?, ?);";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDate(1, Date.valueOf(t.getDate()));
            statement.setString(2, String.valueOf(t.getTime()));
            statement.setString(3, t.getVendor());
            statement.setString(4, t.getDescription());
            statement.setDouble(5, t.getAmount());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated keys
                ResultSet generatedKeys = statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    // Retrieve the auto-incremented ID
                    int orderId = generatedKeys.getInt(1);

                    // get the newly inserted category
                    return getById(orderId);
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Transactions createPayment(Transactions t) {
        String sql = "INSERT INTO transactions(date, time, vendor, description, amount) " +
                " VALUES (?, ?, ?, ?, ?);";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDate(1, Date.valueOf(t.getDate()));
            statement.setString(2, String.valueOf(t.getTime()));
            statement.setString(3, t.getVendor());
            statement.setString(4, t.getDescription());
            statement.setDouble(5, t.getAmount()*-1);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated keys
                ResultSet generatedKeys = statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    // Retrieve the auto-incremented ID
                    int orderId = generatedKeys.getInt(1);

                    // get the newly inserted category
                    return getById(orderId);
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Transactions getById(int orderId) {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId); // Set the parameter for the query

            try (ResultSet row = statement.executeQuery()) {
                if (row.next()) {
                    Transactions t = mapRow(row);
                    return t;
                }
                // If no rows are found, return null or handle it as appropriate for your application
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Return null explicitly if no result is found
        return null;
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