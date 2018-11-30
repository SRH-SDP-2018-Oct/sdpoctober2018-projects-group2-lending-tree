package org.lendingtree.project;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {
    @Test
    public void testDatabaseInput() {
        Customer dbInputCustomer = new Customer();
        Customer dbOutputCustomer;

        try {
            dbInputCustomer.firstName = "Cassie";
            dbInputCustomer.lastName = "Alentorn Vejar";
            dbInputCustomer.password = "test_cassie_password";
            dbInputCustomer.rating = 15;
            dbInputCustomer.identificationNumber = "AA134567";
            dbInputCustomer.taxDetails = 1;
            dbInputCustomer.paySlip = 0;
            dbInputCustomer.currentExpenses = 1;
            dbInputCustomer.phone = "+56912345678";
            dbInputCustomer.address = "Los Militares 1234, Santiago, Chile";
            dbInputCustomer.customerTypeId = 3;
            dbInputCustomer.customerType = ConnectMSSQLServer.getCustomerType(dbInputCustomer.customerTypeId);
            dbInputCustomer.email = "cassie@mail.com";

            ConnectMSSQLServer.insertCustomer(dbInputCustomer);
            dbOutputCustomer = ConnectMSSQLServer.getCustomer(dbInputCustomer.email);
            Assert.assertEquals(dbInputCustomer, dbOutputCustomer);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
