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

            dbInputCustomer.id = ConnectMSSQLServer.insertCustomer(dbInputCustomer);
            dbOutputCustomer = ConnectMSSQLServer.getCustomer(dbInputCustomer.email);

            Assert.assertEquals(dbInputCustomer.firstName, dbOutputCustomer.firstName);
            Assert.assertEquals(dbInputCustomer.lastName, dbOutputCustomer.lastName);
            Assert.assertEquals(dbInputCustomer.password, dbOutputCustomer.password);
            // Assert.assertEquals(dbInputCustomer.rating, dbOutputCustomer.rating);
            Assert.assertEquals(dbInputCustomer.identificationNumber, dbOutputCustomer.identificationNumber);
            Assert.assertEquals(dbInputCustomer.taxDetails, dbOutputCustomer.taxDetails);
            Assert.assertEquals(dbInputCustomer.paySlip, dbOutputCustomer.paySlip);
            Assert.assertEquals(dbInputCustomer.currentExpenses, dbOutputCustomer.currentExpenses);
            Assert.assertEquals(dbInputCustomer.phone, dbOutputCustomer.phone);
            Assert.assertEquals(dbInputCustomer.address, dbOutputCustomer.address);
            Assert.assertEquals(dbInputCustomer.customerType, dbOutputCustomer.customerType);
            Assert.assertEquals(dbInputCustomer.customerTypeId, dbOutputCustomer.customerTypeId);
            Assert.assertEquals(dbInputCustomer.email, dbOutputCustomer.email);
            Assert.assertEquals(dbInputCustomer.id, dbOutputCustomer.id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testDataBaseOutput(){
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
            dbInputCustomer.id = 4;

            dbOutputCustomer = ConnectMSSQLServer.getCustomer(dbInputCustomer.email);

            Assert.assertEquals(dbInputCustomer.firstName, dbOutputCustomer.firstName);
            Assert.assertEquals(dbInputCustomer.lastName, dbOutputCustomer.lastName);
            Assert.assertEquals(dbInputCustomer.password, dbOutputCustomer.password);
           // Assert.assertEquals(dbInputCustomer.rating, dbOutputCustomer.rating);
            Assert.assertEquals(dbInputCustomer.identificationNumber, dbOutputCustomer.identificationNumber);
            Assert.assertEquals(dbInputCustomer.taxDetails, dbOutputCustomer.taxDetails);
            Assert.assertEquals(dbInputCustomer.paySlip, dbOutputCustomer.paySlip);
            Assert.assertEquals(dbInputCustomer.currentExpenses, dbOutputCustomer.currentExpenses);
            Assert.assertEquals(dbInputCustomer.phone, dbOutputCustomer.phone);
            Assert.assertEquals(dbInputCustomer.address, dbOutputCustomer.address);
            Assert.assertEquals(dbInputCustomer.customerType, dbOutputCustomer.customerType);
            Assert.assertEquals(dbInputCustomer.customerTypeId, dbOutputCustomer.customerTypeId);
            Assert.assertEquals(dbInputCustomer.email, dbOutputCustomer.email);
            Assert.assertEquals(dbInputCustomer.id, dbOutputCustomer.id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
