package org.lendingtree.project;

import java.util.ArrayList;
import java.util.Scanner;

public class Loan {

    private int loanId;
    private int customerId;
    private int productId;
    private int loanStatusId;
    private String loanDateApplied;

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
            String choice;
            ArrayList<Integer> periods = new ArrayList<>();

            System.out.println("----Lending Tree: Loans menu---- \n");

            boolean flagMenuLoan;

            do {
                flagMenuLoan=false;
                Scanner input = new Scanner(System.in);

                System.out.println("\nPlease select one of the following options:\n" +
                        "1) Display loans\n"+
                        "2) Generate report\n");
                choice = input.next();

                switch (choice) {
                    default:
                        System.out.println("Invalid entry.\n");
                        flagMenuLoan=true;
                        break;

                    case "1":
                        if (isCustomer==true){
                            LoanDatabase.printCustomerLoans(userId);
                        } else {
                            LoanDatabase.printRepresentativeLoans(userId);
                            System.out.println("Select one option:\n" +
                                    "1) Edit loans\n" +
                                    "2) Go back");
                            String changeStatus=input.next();
                            switch (changeStatus){
                                case "1":
                                    System.out.println("Enter the ID of the loan you want to edit\n");
                                    String loanId=input.next();
                                    System.out.println("Enter the ID of one of the following loan status\n");
                                    LoanDatabase.printLoansStatus();
                                    String loanIdStatus=input.next();
                                    LoanDatabase.updateLoan(Integer.parseInt(loanId),Integer.parseInt(loanIdStatus));
                                    break;

                            }

                        }

                        break;

                    case "2":
                        System.out.println("\nPlease select one of the following options:\n" +
                                "1) Generate complete report\n" +
                                "2) Generate report from a period of time\n");
                        choice = input.next();

                        switch (choice) {
                            case "1":
                                LoanDatabase.generateReport(userId, isCustomer, periods);
                                break;

                            case "2":
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
                        break;
                }
            } while (flagMenuLoan == true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
