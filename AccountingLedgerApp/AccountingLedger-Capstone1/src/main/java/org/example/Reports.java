package org.example;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Reports {
    public void showReports(List<Transaction> transactions) {
        while (true) {

            System.out.println("Select option:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month)");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous year");
            System.out.print("5) Search by vendor: ");
            System.out.println("Enter Choice: ");
            //String choice = scanner.next().toUpperCase();
            Scanner scanner = new Scanner(System.in);
            int choices = scanner.nextInt();
            LocalDate currentDate = LocalDate.now();
            int currentMonth = currentDate.getMonthValue();
            int currentYear = currentDate.getYear();


//            private static String choices = "0";

            switch (choices) {
                case 1:
                    List<Transaction> monthToDateTransactions = new ArrayList<>();
                    for (Transaction transaction : transactions) {
                        LocalDate transactionDate = transaction.getDate();

                        if (transactionDate.getYear() == currentYear && transactionDate.getMonthValue() == currentMonth) {
                            monthToDateTransactions.add(transaction);
                        }
                    }

                    System.out.println("Month to Date Transactions: ");
                    for (Transaction transaction : monthToDateTransactions) {
                        System.out.println(transaction.getType() + " " + transaction.getDate() + " " + transaction.getTime() + " " + transaction.getDescription() + " " + transaction.getVendor() + " $" + transaction.getAmount());

                    }
                    if (monthToDateTransactions.isEmpty()) {
                        System.out.println("No transactions found. ");
                    }

                    break;

                //monthToDate

                case 2:
                    // previousMonth
                    LocalDate firstDayOfPrevMonth = LocalDate.of(currentYear, currentMonth - 1, 1);
                    LocalDate lastDayOfPrevMonth = firstDayOfPrevMonth.withDayOfMonth(firstDayOfPrevMonth.lengthOfMonth());
                    List<Transaction> prevMonthTransactions = new ArrayList<>();
                    for (Transaction transaction : transactions) {
                        LocalDate transactionDate = transaction.getDate();

                        if (transactionDate.isAfter(firstDayOfPrevMonth.minusDays(1)) && transactionDate.isBefore(lastDayOfPrevMonth.plusDays(1))) {
                            prevMonthTransactions.add(transaction);

                        } else if (prevMonthTransactions.isEmpty()) {
                            System.out.println("No transactions found. ");
                        }
                    }
                    System.out.println("Previous month transactions: ");
                    for (Transaction prevMonthTransaction : prevMonthTransactions) {
                        System.out.println(prevMonthTransaction.getType() + " " + prevMonthTransaction.getDate() + " " + prevMonthTransaction.getDescription() + " " + prevMonthTransaction.getVendor() + " $" + prevMonthTransaction.getAmount());
                    }
                    break;
                case 3:
                    // yearToDate
                    LocalDate today = LocalDate.now();
                    LocalDate firstDayOfTheYear = LocalDate.of(currentYear, 1, 1);
                    List<Transaction> yearToDateTransactions = new ArrayList<>();
                    for (Transaction transaction : transactions) {
                        LocalDate transactionDate = transaction.getDate();
                        if ((transactionDate.getYear() == currentYear) && transactionDate.isEqual(firstDayOfTheYear) || transactionDate.isAfter(firstDayOfTheYear) && transactionDate.isBefore(today) || transactionDate.isEqual(today)) {
                            yearToDateTransactions.add(transaction);

                        }
                    }

                    System.out.println("Year-to-Date transactions: ");
                    for (Transaction yearToDateTransaction : yearToDateTransactions) {
                        System.out.println(yearToDateTransaction.getType() + " " + yearToDateTransaction.getDate() + " " + yearToDateTransaction.getTime() + " " + yearToDateTransaction.getVendor() + " " + yearToDateTransaction.getDescription() + " " + yearToDateTransaction.getAmount());
                    }
                    if (yearToDateTransactions.isEmpty()) {
                        System.out.println("No transactions found. ");
                    }
                    break;
                case 4:
                    //lastYearsTransactions
                    LocalDate lastYear = currentDate.withYear(currentYear - 1);
                    List<Transaction> lastYearTransactions = new ArrayList<>();
                    for (Transaction transaction : transactions) {
                        LocalDate transactionDate = transaction.getDate();
                        if (transactionDate.isEqual(lastYear)) {
                            lastYearTransactions.add(transaction);

                            System.out.println("Last Year's transactions: ");
                            for (Transaction lastYearTransaction : lastYearTransactions) {
                                System.out.println(lastYearTransaction.getType() + " " + lastYearTransaction.getDate() + " " + lastYearTransaction.getDescription() + " " + lastYearTransaction.getVendor() + " $" + lastYearTransaction.getAmount());
                            }

                        }
                    }
                    if (lastYearTransactions.isEmpty()) {
                        System.out.println("No transactions found. ");
                    }
                    break;
                case 5:
//                TransactionsByVendor
                    System.out.println("Please enter the vendor: ");
                    scanner.nextLine();
                    String searchVendor = scanner.nextLine();
                    List<Transaction> vendorTransactions = new ArrayList<>();
                    for (Transaction transaction : transactions) {
                        if (searchVendor.equalsIgnoreCase(transaction.getVendor())) {
                            vendorTransactions.add(transaction);
                        }
                    }

                    System.out.println("Transactions for vendor: " + searchVendor);
                    for (Transaction transaction : vendorTransactions) {
                        System.out.println(transaction.getType() + " " + transaction.getDate() + " " + transaction.getTime() + " " + transaction.getDescription() + " " + transaction.getVendor() + " $" + transaction.getAmount());
                    }

                    if (vendorTransactions.isEmpty()) {
                        System.out.println("No transactions found. ");
                    }

                    break;

                case 6:
                    System.out.println("Custom Search:");
                    System.out.println("Start Date (yyyy-MM-dd)");
                    scanner.nextLine();
                    String searchStartDateStr = scanner.nextLine();
                    LocalDate searchStartDate = LocalDate.parse(searchStartDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
                    System.out.println("End Date (yyyy-MM-dd)");
                    String searchEndDateStr = scanner.nextLine();
                    LocalDate searchEndDate = LocalDate.parse(searchEndDateStr, DateTimeFormatter.ISO_LOCAL_DATE);

                    System.out.println("Description: ");
                    String searchDescription = scanner.nextLine();

                    System.out.println("Vendor: ");
                    String customSearchVendor = scanner.nextLine();
                    System.out.println("Amount: ");
                    double searchAmount = scanner.nextDouble();
                    scanner.nextLine();

                    List<Transaction> customSearchTransactions = new ArrayList<>();
                    for (Transaction transaction : transactions) {
                        if ((transaction.getDate().isAfter(searchStartDate) ||
                                transaction.getDate().isEqual(searchStartDate) && transaction.getDate().isBefore(searchEndDate) ||
                                transaction.getDate().isEqual(searchEndDate)) &&
                                transaction.getVendor().equalsIgnoreCase(customSearchVendor) &&
                                transaction.getDescription().equalsIgnoreCase(searchDescription) && transaction.getAmount() == searchAmount) {
                            customSearchTransactions.add(transaction);

                        }
                    }

                    if (!customSearchTransactions.isEmpty()) {
                        System.out.println("Custom Search Results: ");
                        for (Transaction customTransaction : customSearchTransactions) {
                            System.out.println(customTransaction.getType() + " " + customTransaction.getDate() + " " + customTransaction.getTime() + " " + customTransaction.getDescription() + " " + customTransaction.getVendor() + " $" + customTransaction.getAmount());
                        }
                    } else {
                        System.out.println("No transactions found. ");
                    }

                    break;

            }
        }

    }
}
