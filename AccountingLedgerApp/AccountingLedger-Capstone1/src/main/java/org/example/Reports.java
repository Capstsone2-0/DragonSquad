package org.example;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reports {
    public  void showReports(List<Transaction> transactions) {
        while (true) {

            System.out.println("Select option:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month)");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous year");
            System.out.print("5) Search by vendor: ");
            //String choice = scanner.next().toUpperCase();
            Scanner scanner = new Scanner(System.in);
            int choices = scanner.nextInt();
            LocalDate currentDate = LocalDate.now();
            int currentMonth = currentDate.getMonthValue();
            int currentYear = currentDate.getYear();


//            private static String choices = "0";

            switch (choices) {
                case 1:

                    //monthToDate
                    break;
                case 2:
                    // previousMonth
                    break;
                case 3:
                    // yearToDate
                    break;
                case 4:
                    //lastYearsTransactions
                    break;
                case 5:
//                TransactionsByVendor
                case 6:
                    break;

            }
        }

    }
}
