package org.lendingtree.project;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void getDatabaseUrlFromFileTest(){
        String content = "";
        String filePath = "C:\\Users\\Gaston\\Documents\\GitHub\\sdpoctober2018-projects-group2-lending-tree\\lending_tree_application\\database_config.txt";
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println(content);

        Assert.assertEquals("jdbc:sqlserver://localhost:1433;DatabaseName=lendingtree;allowMultiQueries=true",content);
    }

    @Test
    public void getDatabaseUrlFromFileTest2(){
        String filePath = ".\\database_config.txt";
        BufferedReader abc;
        List<String> lines = new ArrayList<String>();
        String line;
        String data[] = new String[3];
        try{
            int stringArrayPosition = 0;
            abc = new BufferedReader(new FileReader(filePath));
            while((line = abc.readLine()) != null) {
                lines.add(line);
                data[stringArrayPosition] = line;
                System.out.println(data[stringArrayPosition]);
            }
            abc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
