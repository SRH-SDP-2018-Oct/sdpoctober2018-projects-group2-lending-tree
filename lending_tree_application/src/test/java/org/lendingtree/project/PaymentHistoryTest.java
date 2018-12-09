package org.lendingtree.project;

import org.junit.Test;


public class PaymentHistoryTest {




    @Test
    public void getPaymentHistoryYes() {

        String inputYN = "Y";




       try {
             if (inputYN.equalsIgnoreCase("Y")) {
                PaymentDisplayDatabase.displayPaymentHistoryAll();
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
                PaymentDisplayDatabase.displayPaymentHistory(inputCustomerId);
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
            PaymentDisplayDatabase.displayPaymentHistoryCustomerDatabase(userId);
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
