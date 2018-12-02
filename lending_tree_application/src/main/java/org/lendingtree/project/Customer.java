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
    public static final String dbColumnCustomerId = "customer_id";
    public static final String dbColumnCustomerTypeId = "customer_type_id";
    public static final String dbColumnCustomerLastName = "customer_last_name";
    public static final String dbColumnCustomerFirstName = "customer_first_name";
    public static final String dbColumnCustomerAddress = "customer_address";
    public static final String dbColumnCustomerEmail = "customer_email";
    public static final String dbColumnCustomerPhone = "customer_phone";
    public static final String dbColumnCustomerCurrentExpenses = "customer_current_expenses";
    public static final String dbColumnCustomerPaySlip = "customer_pay_slip";
    public static final String dbColumnCustomerTaxDetails = "customer_tax_details";
    public static final String dbColumnCustomerIdentificationNumber = "customer_identification_number";
    public static final String dbColumnCustomerRating = "customer_rating";
    public static final String dbColumnCustomerPassword = "customer_password";

    public void registerNewCustomer() throws SQLException{
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
