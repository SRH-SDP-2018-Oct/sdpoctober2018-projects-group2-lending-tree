package org.lendingtree.project;

import org.junit.Test;

import java.util.Date;


public class PaymentHistoryTest {




    @Test
    public void getPaymentHistoryYes() {

        String inputYN = "Y";




       try {
             if (inputYN.equalsIgnoreCase("Y")) {
                PaymentHistoryDatabase.displayPaymentHistoryAll();
            }


        } catch (Exception e) {
            e.printStackTrace();
       }
    }

    @Test
    public void getPaymentHistoryNo(){


        int inputCustomerId =1;
        String inputYN = "N";




        try {
            if(inputYN.equalsIgnoreCase("N")) {
                PaymentHistoryDatabase.displayPaymentHistory(inputCustomerId);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testCustomerPaymentHistory(){

        PaymentHistory paymentHistory = new PaymentHistory();
        int userId = 1;

        try {
            PaymentHistoryDatabase.displayPaymentHistoryCustomerDatabase(userId);
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Test
    public void insertPaymentHistoryTest() {
        PaymentHistory paymentHistory = new PaymentHistory();
        int loanId = 1;
        Double paymentAmount = 120.25;
        Date paymentDate = new Date();
        String paymentType = "Credit";

        try {
            //add
            paymentHistory.setPaymentAmount(paymentAmount);
            //add

            PaymentHistoryDatabase.insertIntoPaymentHistory();

        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Test
    public void dateTest() {
        System.out.println(new Date().getMonth());
    }
}
