package org.lendingtree.project;

import java.sql.SQLException;
import java.util.Scanner;

public class Customer extends User {
    public String customerType;
    public int customerTypeId;
    public String address;
    public int currentExpenses;
    public int paySlip;
    public int taxDetails;
    public String identificationNumber;
    public float rating;

    public void getUserInputCustomer() throws SQLException{
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter your Last Name: ");
        lastName = userInput.nextLine();
        System.out.println(lastName);
        System.out.println("Please enter your First Name: ");
        firstName = userInput.nextLine();
        System.out.println(firstName);
        ConnectMSSQLServer.listCustomerTypes();
        System.out.println("Please select a number for the customer type: ");
        customerTypeId = Integer.parseInt(userInput.nextLine());
        System.out.println(customerTypeId);
        customerType = ConnectMSSQLServer.getCustomerType(customerTypeId);
        System.out.println(customerType);
        System.out.println("Please enter your email address: ");
        email = userInput.nextLine();
        System.out.println("Please enter your home address: ");
        address = userInput.nextLine();
        System.out.println("Please enter your phone number: ");
        phone = userInput.nextLine();
        currentExpenses = 0;
        paySlip = 0;
        taxDetails = 0;
        System.out.println("Please enter your national identification number: ");
        identificationNumber = userInput.nextLine();
        System.out.println("Please enter your credit rating: ");
        rating = Float.parseFloat(userInput.nextLine());
        System.out.println("Please enter a password for your account: ");
        password = userInput.nextLine();
    }


}
