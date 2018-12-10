package org.lendingtree.project;

import org.junit.Test;

public class CustomerDocumentsTest {
    @Test
    public void showDocumentsStatusTest(){
        Customer customer;

        try{
            customer = CustomerDatabase.getCustomer("cassie@mail.com");

            CustomerDocuments.showDocumentsStatus(customer);

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void updateDocumentsStatusTest(){
        final int DOCUMENT_SENT = 1;
        final int DOCUMENT_NOT_SENT = 0;
        final int TAX_DETAILS = 1;
        final int PAY_SLIP = 2;
        final int CURRENT_EXPENSES = 3;

        Customer customer;
        int document;
        int status;

        try{
            customer = CustomerDatabase.getCustomer("cassie@mail.com");
            CustomerDocuments.showDocumentsStatus(customer);
            document = selectDocumentToUpdate(CURRENT_EXPENSES);
            status = selectDocumentStatus(DOCUMENT_NOT_SENT);

            switch (document){
                case 1: customer.setTaxDetails(status); break;
                case 2: customer.setPaySlip(status); break;
                case 3: customer.setCurrentExpenses(status); break;
            }

            CustomerDocuments.showDocumentsStatus(customer);

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static int selectDocumentToUpdate(int simulation){
        int userInput;
        int numberOfTries = 0;

        System.out.println("Please select the number corresponding to the document status to update: \n 1. Tax Details \n 2. Pay Slip \n 3. Current Expenses");

        do{
            if(numberOfTries > 0){
                System.out.println("Invalid input. Please select a number between 1 and 3");
            }
            System.out.println("Please enter your selection:");
            userInput = simulation;
            numberOfTries++;
        }while (userInput < 1 || userInput > 3);

        return userInput;
    }

    private static int selectDocumentStatus(int simulation){
        int userInput;
        int numberOfTries = 0;

        System.out.println("Please select the number corresponding to the new status: \n 0. Document Not Sent \n 1. Document Sent");

        do{
            if(numberOfTries > 0){
                System.out.println("Invalid input. Please select 0 or 1");
            }
            System.out.println("Please enter your selection:");
            userInput = simulation;
            numberOfTries++;
        }while (userInput < 0 || userInput > 1);

        return userInput;
    }
}
