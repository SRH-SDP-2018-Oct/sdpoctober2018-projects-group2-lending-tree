package org.lendingtree.project;

import org.junit.Test;


public class PaymentHistoryTest {


    @Test
    public void getPaymentHistoryRepresentative() {

        int userId = 2;

            PaymentHistoryDatabase.displayPaymentHistoryRepresentative(userId);

    }

    @Test
    public void getPaymentHistorySort() {
        int userId = 2;
        int inputCustomerId = 5;


        try {

             PaymentHistoryDatabase.displayPaymentHistoryRepresentativeSort(userId, inputCustomerId);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testCustomerPaymentHistory() {

        int userId = 1;

        try {
            PaymentHistoryDatabase.displayPaymentHistoryCustomer(userId);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

