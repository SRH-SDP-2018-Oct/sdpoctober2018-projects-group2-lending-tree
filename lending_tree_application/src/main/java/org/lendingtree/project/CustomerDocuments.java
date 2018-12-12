package org.lendingtree.project;

import java.util.Scanner;

public abstract class CustomerDocuments {
    final static String DOCUMENT_STATUS_1 = "Document Sent";
    final static String DOCUMENT_STATUS_0 = "Document Not Sent";

    public static void showDocumentsStatus(Customer customer){
        String status;

        System.out.println("---------------DOCUMENTS STATUS----------------");
        System.out.println("Customer: " + customer.getLastName() + ", " + customer.getFirstName());

        status = getDocumentStatus(customer.getTaxDetails());
        System.out.println("Tax Details: " + status);

        status = getDocumentStatus(customer.getPaySlip());
        System.out.println("Pay Slip: " + status);

        status = getDocumentStatus(customer.getCurrentExpenses());
        System.out.println("Current Expenses: " + status);
    }

    private static String getDocumentStatus(int status){
        if(status == 1) return DOCUMENT_STATUS_1;
        return  DOCUMENT_STATUS_0;
    }

    public static void updateDocumentStatus(Customer customer){
        int document;
        int status;

        document = selectDocumentToUpdate();
        status = selectDocumentStatus();

        switch (document){
            case 1: customer.setTaxDetails(status); break;
            case 2: customer.setPaySlip(status); break;
            case 3: customer.setCurrentExpenses(status); break;
        }
    }

    private static int selectDocumentToUpdate(){
        final int MIN_VALUE = 1;
        final int MAX_VALUE = 3;

        System.out.println("Please select the number corresponding to the document status to update: \n 1. Tax Details \n 2. Pay Slip \n 3. Current Expenses");

        return InputValidationTools.inputNumber(MIN_VALUE, MAX_VALUE);
    }

    private static int selectDocumentStatus(){
        final int MIN_VALUE = 0;
        final int MAX_VALUE = 1;

        System.out.println("Please select the number corresponding to the new status: \n 0. Document Not Sent \n 1. Document Sent");

        return InputValidationTools.inputNumber(MIN_VALUE, MAX_VALUE);
    }
}
