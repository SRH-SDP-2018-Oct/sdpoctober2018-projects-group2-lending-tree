package org.lendingtree.project;

import java.sql.SQLException;
import java.util.Scanner;

public class Customer extends User {
    public String customerType;
    public int customerTypeId = 0;
    public String address;
    public int currentExpenses = 0;
    public int paySlip = 0;
    public int taxDetails = 0;
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
        int maxCustomerTypeValue;
        String passwordConfirmation = "";

        do{
            System.out.println("Please enter your Last Name: ");
            lastName = userInput.nextLine();
        }while (!confirmUserInputString(lastName));

        do{
            System.out.println("Please enter your First Name: ");
            firstName = userInput.nextLine();
        }while (!confirmUserInputString(firstName));

        maxCustomerTypeValue = ConnectMSSQLServer.listCustomerTypes();

        do{
            System.out.println("Please select a valid number for the customer type: ");
            while(!userInput.hasNextInt()) {
                System.out.println("That is not a valid selection");
                userInput.next();
            }
            customerTypeId = userInput.nextInt();
        } while(customerTypeId < 1 || customerTypeId > maxCustomerTypeValue);

        customerType = ConnectMSSQLServer.getCustomerType(customerTypeId);
        System.out.println("You selected: " + customerType);

        do{
            System.out.println("Please enter your email address: ");
            email = userInput.nextLine();
        }while (!confirmUserInputString(email));

        do{
            System.out.println("Please enter your home address: ");
            address = userInput.nextLine();
        }while (!confirmUserInputString(address));

        do{
            System.out.println("Please enter your phone number: ");
            phone = userInput.nextLine();
        }while (!confirmUserInputString(phone));

        do{
            System.out.println("Please enter your national identification number: ");
            identificationNumber = userInput.nextLine();
        }while (!confirmUserInputString(identificationNumber));

        do{
            System.out.println("Please select a valid number for the customer rating: ");
            while(!userInput.hasNextFloat()) {
                System.out.println("That is not a valid selection");
                userInput.next();
            }
            rating = userInput.nextFloat();
        } while(rating < 0);
        System.out.println("Your customer rating is: " + rating);

        do{
            try{
                System.out.println("Please enter your password: ");
                password = EncryptionTools.encryptPassword(userInput.nextLine());
                System.out.println("Please enter your password again to confirm: ");
                passwordConfirmation = EncryptionTools.encryptPassword(userInput.nextLine());
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }while (password.compareTo(passwordConfirmation) != 0);

        ConnectMSSQLServer.insertCustomer(this);
    }

    private Boolean confirmUserInputString(String input){
        char userConfirmation;
        Scanner userInput = new Scanner(System.in);
        do{
            System.out.println("You entered: " + input);
            System.out.println("Is this correct? Y/N");
            userConfirmation = userInput.next().charAt(0);
        } while (userConfirmation != 'Y');
        return true;
    }

}
