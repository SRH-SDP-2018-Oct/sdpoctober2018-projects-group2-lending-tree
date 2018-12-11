package org.lendingtree.project;

import org.junit.Test;


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
        int inputCustomerId =5;
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
    public void testInput(){

        PaymentHistory paymentHistory = new PaymentHistory();
        int userId = 1;

        try {
            PaymentHistoryDatabase.displayPaymentHistoryCustomerDatabase(userId);
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
