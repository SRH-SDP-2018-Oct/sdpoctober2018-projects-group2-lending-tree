package org.lendingtree.project;

import org.junit.Test;

import java.util.ArrayList;

public class LoanTest {

    @Test
    public void createLoanTest() {
        Loan newLoan = new Loan();
        int customerId = 1;
        int productId = 4;
        int loanStatusId = 1;
        String loanDateApplied = "01/01/2018";

        try {
            newLoan.setCustomerId(customerId);
            newLoan.setProductId(productId);
            newLoan.setLoanStatusId(loanStatusId);
            newLoan.setLoanDateApplied(loanDateApplied);

            LoanDatabase.insertLoan(newLoan);

            System.out.println("The loan was added successfully.");

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void printCustomerLoansTest() {

        int customerId = 1;

        try {
            LoanDatabase.printCustomerLoans(customerId);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void printRepresentativeLoansTest() {

        int representativeId = 1;

        try {
            LoanDatabase.printRepresentativeLoans(representativeId);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void printInstitutionLoansTest() {

        int institutionId = 2;

        try {
            LoanDatabase.printInstitutionLoans(institutionId);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void generateReportTest() {

        int userId = 1;
        boolean isCustomer = true;
        ArrayList<Integer> periods = new ArrayList<>();

        periods.add(1);
        periods.add(2018);
        periods.add(4);
        periods.add(2018);

        try {
            LoanDatabase.generateReport(userId, isCustomer, periods);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}