package org.lendingtree.project;

import org.junit.Assert;
import org.junit.Test;

public class ConnectMSSQLServerTest {
    @Test
    public void testDatabaseInput() {
        Customer dbInputCustomer = new Customer();
        Customer dbOutputCustomer;

        try {
            dbInputCustomer.setFirstName("Cassie");
            dbInputCustomer.setLastName("Alentorn Vejar");
            dbInputCustomer.setPassword("test_cassie_password");
            dbInputCustomer.setRating(15f);
            dbInputCustomer.setIdentificationNumber("AA134567");
            dbInputCustomer.setTaxDetails(1);
            dbInputCustomer.setPaySlip(0);
            dbInputCustomer.setCurrentExpenses(1);
            dbInputCustomer.setPhone("+56912345678");
            dbInputCustomer.setAddress("Los Militares 1234, Santiago, Chile");
            dbInputCustomer.setCustomerTypeId(3);
            dbInputCustomer.setCustomerType(CustomerDatabase.getCustomerType(dbInputCustomer.getCustomerTypeId()));
            dbInputCustomer.setEmail("cassie@mail.com");

            dbInputCustomer.setId(CustomerDatabase.insert(dbInputCustomer));
            dbOutputCustomer = CustomerDatabase.getCustomer(dbInputCustomer.getEmail());

            Assert.assertEquals(dbInputCustomer.getFirstName(), dbOutputCustomer.getFirstName());
            Assert.assertEquals(dbInputCustomer.getLastName(), dbOutputCustomer.getLastName());
            Assert.assertEquals(dbInputCustomer.getPassword(), dbOutputCustomer.getPassword());
            // Assert.assertEquals(dbInputCustomer.getRating(), dbOutputCustomer.getRating());
            Assert.assertEquals(dbInputCustomer.getIdentificationNumber(), dbOutputCustomer.getIdentificationNumber());
            Assert.assertEquals(dbInputCustomer.getTaxDetails(), dbOutputCustomer.getTaxDetails());
            Assert.assertEquals(dbInputCustomer.getPaySlip(), dbOutputCustomer.getPaySlip());
            Assert.assertEquals(dbInputCustomer.getCurrentExpenses(), dbOutputCustomer.getCurrentExpenses());
            Assert.assertEquals(dbInputCustomer.getPhone(), dbOutputCustomer.getPhone());
            Assert.assertEquals(dbInputCustomer.getAddress(), dbOutputCustomer.getAddress());
            Assert.assertEquals(dbInputCustomer.getCustomerType(), dbOutputCustomer.getCustomerType());
            Assert.assertEquals(dbInputCustomer.getCustomerTypeId(), dbOutputCustomer.getCustomerTypeId());
            Assert.assertEquals(dbInputCustomer.getEmail(), dbOutputCustomer.getEmail());
            Assert.assertEquals(dbInputCustomer.getId(), dbOutputCustomer.getId());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testDataBaseOutput() {
        Customer dbInputCustomer = new Customer();
        Customer dbOutputCustomer;

        try {
            dbInputCustomer.setFirstName("Cassie");
            dbInputCustomer.setLastName("Alentorn Vejar");
            dbInputCustomer.setPassword("test_cassie_password");
            dbInputCustomer.setRating(15f);
            dbInputCustomer.setIdentificationNumber("AA134567");
            dbInputCustomer.setTaxDetails(1);
            dbInputCustomer.setPaySlip(0);
            dbInputCustomer.setCurrentExpenses(1);
            dbInputCustomer.setPhone("+56912345678");
            dbInputCustomer.setAddress("Los Militares 1234, Santiago, Chile");
            dbInputCustomer.setCustomerTypeId(3);
            dbInputCustomer.setCustomerType(CustomerDatabase.getCustomerType(dbInputCustomer.getCustomerTypeId()));
            dbInputCustomer.setEmail("cassie@mail.com");
            dbInputCustomer.setId(4);

            dbOutputCustomer = CustomerDatabase.getCustomer(dbInputCustomer.getEmail());

            Assert.assertEquals(dbInputCustomer.getFirstName(), dbOutputCustomer.getFirstName());
            Assert.assertEquals(dbInputCustomer.getLastName(), dbOutputCustomer.getLastName());
            Assert.assertEquals(dbInputCustomer.getPassword(), dbOutputCustomer.getPassword());
            // Assert.assertEquals(dbInputCustomer.getRating(), dbOutputCustomer.getRating());
            Assert.assertEquals(dbInputCustomer.getIdentificationNumber(), dbOutputCustomer.getIdentificationNumber());
            Assert.assertEquals(dbInputCustomer.getTaxDetails(), dbOutputCustomer.getTaxDetails());
            Assert.assertEquals(dbInputCustomer.getPaySlip(), dbOutputCustomer.getPaySlip());
            Assert.assertEquals(dbInputCustomer.getCurrentExpenses(), dbOutputCustomer.getCurrentExpenses());
            Assert.assertEquals(dbInputCustomer.getPhone(), dbOutputCustomer.getPhone());
            Assert.assertEquals(dbInputCustomer.getAddress(), dbOutputCustomer.getAddress());
            Assert.assertEquals(dbInputCustomer.getCustomerType(), dbOutputCustomer.getCustomerType());
            Assert.assertEquals(dbInputCustomer.getCustomerTypeId(), dbOutputCustomer.getCustomerTypeId());
            Assert.assertEquals(dbInputCustomer.getEmail(), dbOutputCustomer.getEmail());
            Assert.assertEquals(dbInputCustomer.getId(), dbOutputCustomer.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void customerUpdateTest(){
        Customer dbInputCustomer = new Customer();
        Customer dbOutputCustomer;
        String newAddressValue = "Odenwaldstr. 123, Heidelberg, Germany";
        int newTaxDetailsValue = 1;

        try {
            dbInputCustomer.setFirstName("Cassie");
            dbInputCustomer.setLastName("Alentorn Vejar");
            dbInputCustomer.setPassword("test_cassie_password");
            dbInputCustomer.setRating(15f);
            dbInputCustomer.setIdentificationNumber("AA134567");
            dbInputCustomer.setTaxDetails(1);
            dbInputCustomer.setPaySlip(0);
            dbInputCustomer.setCurrentExpenses(1);
            dbInputCustomer.setPhone("+56912345678");
            dbInputCustomer.setAddress("Los Militares 1234, Santiago, Chile");
            dbInputCustomer.setCustomerTypeId(3);
            dbInputCustomer.setCustomerType(CustomerDatabase.getCustomerType(dbInputCustomer.getCustomerTypeId()));
            dbInputCustomer.setEmail("cassie@mail.com");

            dbInputCustomer.setId(CustomerDatabase.insert(dbInputCustomer));
            dbOutputCustomer = CustomerDatabase.getCustomer(dbInputCustomer.getEmail());

            System.out.println(dbOutputCustomer.getAddress());
            System.out.println(dbOutputCustomer.getTaxDetails());

            Assert.assertEquals(dbInputCustomer.getAddress(), dbOutputCustomer.getAddress());
            Assert.assertEquals(dbInputCustomer.getTaxDetails(), dbOutputCustomer.getTaxDetails());


            dbInputCustomer.setAddress(newAddressValue);
            dbInputCustomer.setTaxDetails(newTaxDetailsValue);

            CustomerDatabase.update("customer_address", dbInputCustomer.getAddress(), dbInputCustomer.getId());
            CustomerDatabase.update("customer_tax_details", String.valueOf(dbInputCustomer.getTaxDetails()), dbInputCustomer.getId());

            dbOutputCustomer = CustomerDatabase.getCustomer(dbInputCustomer.getEmail());

            System.out.println(dbOutputCustomer.getAddress());
            System.out.println(dbOutputCustomer.getTaxDetails());

            Assert.assertEquals(dbInputCustomer.getAddress(), dbOutputCustomer.getAddress());
            Assert.assertEquals(dbInputCustomer.getTaxDetails(), dbOutputCustomer.getTaxDetails());

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
