package org.lendingtree.project;

import org.junit.Test;

public class LoanTest {

    @Test
    public void createLoanTest() {
        Loan newLoan = new Loan();
        //int loanId = 1;
        int customerId = 1;
        int productId = 1;
        int loanStatusId = 1;
        String loanDateApplied = "01/01/2018";

        try {
            //newLoan.setLoanId(loanId);
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
    public void printCustomerActiveLoansTest() {

        int customerId = 1;

        try {
            LoanDatabase.printLoans(customerId);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
