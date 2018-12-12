package org.lendingtree.project;

import java.util.ArrayList;
import java.util.Scanner;

public class Loan {

    public int loanId;
    public int customerId;
    public int productId;
    public int loanStatusId;
    public String loanDateApplied;

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getLoanStatusId() {
        return loanStatusId;
    }

    public void setLoanStatusId(int loanStatusId) {
        this.loanStatusId = loanStatusId;
    }

    public String getLoanDateApplied() {
        return loanDateApplied;
    }

    public void setLoanDateApplied(String loanDateApplied) {
        this.loanDateApplied = loanDateApplied;
    }

    public static void goMenuLoan(int userId, String userType) {
        try {
            boolean isCustomer=false;
            if (userType == App.USER_TYPE_CUSTOMER){isCustomer=true;}
            int choice;
            ArrayList<Integer> periods = new ArrayList<>();

            System.out.println("----Lending Tree: Loans menu---- \n");

            boolean flag;

            do {
                Scanner input = new Scanner(System.in);

                System.out.println("\nPlease select one of the following options:\n" +
                        "1) Display loans\n" +
                        "2) Generate report\n");
                choice = input.nextInt();

                switch (choice) {
                    default:
                        System.out.println("Invalid entry.\n");
                        flag = false;
                        break;

                    case 1:
                        System.out.println("Work in progress.\n");
                        LoanDatabase.printCustomerLoans(userId);
                        flag = false;
                        break;

                    case 2:
                        System.out.println("\nPlease select one of the following options:\n" +
                                "1) Generate complete report\n" +
                                "2) Generate report from a period of time\n");
                        choice = input.nextInt();
                        flag = false;

                        switch (choice) {
                            case 1:
                                LoanDatabase.generateReport(userId, isCustomer, periods);
                                break;

                            case 2:
                                System.out.println("\nPlease enter the month of the starting period\n" +
                                        "1) January\n" +
                                        "2) February\n" +
                                        "3) March\n" +
                                        "4) April\n" +
                                        "5) May\n" +
                                        "6) June\n" +
                                        "7) July\n" +
                                        "8) August\n" +
                                        "9) September\n" +
                                        "10) October\n" +
                                        "11) November\n" +
                                        "12) December\n");
                                periods.add(input.nextInt());
                                System.out.println("\nPlease enter the year of the starting period\n");
                                periods.add(input.nextInt());
                                System.out.println("\nPlease enter the month of the ending period\n" +
                                        "1) January\n" +
                                        "2) February\n" +
                                        "3) March\n" +
                                        "4) April\n" +
                                        "5) May\n" +
                                        "6) June\n" +
                                        "7) July\n" +
                                        "8) August\n" +
                                        "9) September\n" +
                                        "10) October\n" +
                                        "11) November\n" +
                                        "12) December\n");
                                periods.add(input.nextInt());
                                System.out.println("\nPlease enter the year of the ending period\n");
                                periods.add(input.nextInt());
                                LoanDatabase.generateReport(userId, isCustomer, periods);
                                break;
                        }
                }
            } while (flag = false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
