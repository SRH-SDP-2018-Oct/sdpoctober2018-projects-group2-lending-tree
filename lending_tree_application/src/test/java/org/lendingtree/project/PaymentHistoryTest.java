package org.lendingtree.project;

import org.junit.Test;


public class PaymentHistoryTest {




    @Test
    public void getPaymentHistory() {
        int inputCustomerId = 1 ;




        try {
            PaymentDisplayDatabase.displayPaymentHistory(inputCustomerId);
            System.out.println("Successful");


        } catch (Exception e) {
            e.printStackTrace();
       }
    }
}
