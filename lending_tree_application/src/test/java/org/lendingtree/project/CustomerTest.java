package org.lendingtree.project;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void registerUserTest() {
        Customer newCustomer = new Customer();
        String firstName = "Cassie";
        String lastName = "Alentorn Vejar";
        String password = "test_cassie_password";
        float rating = 15;
        String identificationNumber = "AA134567";
        int taxDetails = 1;
        int paySlip = 0;
        int currentExpenses = 1;
        String phone = "+56912345678";
        String address = "Los Militares 1234, Santiago, Chile";
        int customerTypeId = 3;
        String customerType = "HOME";
        String email = "cassie@mail.com";
        int id = 1;




        try{
            newCustomer.taxDetails = taxDetails;
            newCustomer.paySlip = paySlip;
            newCustomer.currentExpenses = currentExpenses;

            int maxCustomerTypeValue;
            String passwordConfirmation;

            do{
                System.out.println("Please enter your Last Name: ");
                newCustomer.lastName = lastName;
            }while (!confirmUserInputString(newCustomer.lastName));

            do{
                System.out.println("Please enter your First Name: ");
                newCustomer.firstName = firstName;
            }while (!confirmUserInputString(newCustomer.firstName));

            maxCustomerTypeValue = ConnectMSSQLServer.listCustomerTypes();
            System.out.println("Maximum id of Customer Type is: " + maxCustomerTypeValue);

            newCustomer.customerTypeId = customerTypeId;

            newCustomer.customerType = ConnectMSSQLServer.getCustomerType(newCustomer.customerTypeId);
            System.out.println("You selected: " + newCustomer.customerType);

            do{
                System.out.println("Please enter your email address: ");
                newCustomer.email = email;
            }while (!confirmUserInputString(newCustomer.email));

            do{
                System.out.println("Please enter your home address: ");
                newCustomer.address = address;
            }while (!confirmUserInputString(newCustomer.address));

            do{
                System.out.println("Please enter your phone number: ");
                newCustomer.phone = phone;
            }while (!confirmUserInputString(newCustomer.phone));

            do{
                System.out.println("Please enter your national identification number: ");
                newCustomer.identificationNumber = identificationNumber;
            }while (!confirmUserInputString(newCustomer.identificationNumber));

            System.out.println("Please select a valid number for the customer rating: ");

            newCustomer.rating = rating;

            System.out.println("Your customer rating is: " + newCustomer.rating);

            do{
                System.out.println("Please enter your password: ");
                newCustomer.password = EncryptionTools.encryptPassword(password);
                System.out.println("Please enter your password again to confirm: ");
                passwordConfirmation = EncryptionTools.encryptPassword(password);
            }while (newCustomer.password.compareTo(passwordConfirmation) != 0);

            id = ConnectMSSQLServer.insertCustomer(newCustomer);

            Assert.assertEquals(firstName, newCustomer.firstName);
            Assert.assertEquals(lastName, newCustomer.lastName);
            Assert.assertEquals(EncryptionTools.encryptPassword(password), newCustomer.password);
            // Assert.assertEquals(rating, newCustomer.rating);
            System.out.println("Input: " + rating + " --- Customer: " + newCustomer.rating);
            Assert.assertEquals(identificationNumber, newCustomer.identificationNumber);
            Assert.assertEquals(taxDetails, newCustomer.taxDetails);
            Assert.assertEquals(paySlip, newCustomer.paySlip);
            Assert.assertEquals(currentExpenses, newCustomer.currentExpenses);
            Assert.assertEquals(phone, newCustomer.phone);
            Assert.assertEquals(address, newCustomer.address);
            Assert.assertEquals(customerType, newCustomer.customerType);
            Assert.assertEquals(customerTypeId, newCustomer.customerTypeId);
            Assert.assertEquals(email, newCustomer.email);
            Assert.assertEquals(id, newCustomer.id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private Boolean confirmUserInputString(String input){
        System.out.println("You entered: " + input);
        return true;
    }

    @Test
    public void loginTest() {
        String correctEmail = "cassie@mail.com";
        String incorrectEmail = "nocassie@mail.com";
        String passwordAttempt[] = new String[4];

        String message = "Login successful.";
        Customer loginCustomer = new Customer();
        int remainingAttempts = 3;
        String userInputPassword = new String();
        int currentPasswordAttempt = 0;

        passwordAttempt[0] = "test_fail";
        passwordAttempt[1] = "test_fail2";
        passwordAttempt[2] = "test_cassie_passworD";
        passwordAttempt[3] = "test_cassie_password"; //correct password
        loginCustomer.id = 0;

        System.out.println("Please enter the user email: ");
        loginCustomer.email = incorrectEmail;
        System.out.println(loginCustomer.email);
        try {
            loginCustomer = ConnectMSSQLServer.getCustomer(loginCustomer.email);
            do{
                if(remainingAttempts < 3)
                    System.out.println("Invalid password, please try again. " + remainingAttempts + " remaining attempts");
                remainingAttempts--;
                System.out.println("Please enter the user password: ");
                userInputPassword = EncryptionTools.encryptPassword(passwordAttempt[currentPasswordAttempt]);
                System.out.println("Input password: " + userInputPassword);
                System.out.println("Remaining attempts: " + remainingAttempts);
                System.out.println("Current password attempt: " + currentPasswordAttempt);
                currentPasswordAttempt++;
            }while(remainingAttempts > 0 && userInputPassword.compareTo(loginCustomer.password)!=0);

        } catch (Exception e) {
            message = "Invalid user. Email not found";
        }

        System.out.println(message);

        remainingAttempts = 3;
        currentPasswordAttempt = 0;
        message = "Login successful.";
        loginCustomer.email = correctEmail;
        System.out.println("===================== NEW SCENARIO ==================");
        System.out.println(loginCustomer.email);
        try {
            loginCustomer = ConnectMSSQLServer.getCustomer(loginCustomer.email);
            do{
                if(remainingAttempts < 3)
                    System.out.println("Invalid password, please try again. " + remainingAttempts + " remaining attempts");
                remainingAttempts--;
                System.out.println("Please enter the user password: ");
                userInputPassword = EncryptionTools.encryptPassword(passwordAttempt[currentPasswordAttempt]);
                System.out.println("Input password: " + userInputPassword);
                System.out.println("Remaining attempts: " + remainingAttempts);
                System.out.println("Current password attempt: " + currentPasswordAttempt);
                currentPasswordAttempt++;
            }while(remainingAttempts > 0 && userInputPassword.compareTo(loginCustomer.password)!=0);

        } catch (Exception e) {
            message = "Invalid email address. User does not exist.";
        }

        if(userInputPassword.compareTo(loginCustomer.password)!=0)
            message = "Invalid password. No attempts remaining.";

        System.out.println(message);

        remainingAttempts = 4;
        currentPasswordAttempt = 0;
        message = "Login successful.";
        loginCustomer.email = correctEmail;
        System.out.println("===================== NEW SCENARIO ==================");
        System.out.println(loginCustomer.email);
        try {
            loginCustomer = ConnectMSSQLServer.getCustomer(loginCustomer.email);
            do{
                if(remainingAttempts < 3)
                    System.out.println("Invalid password, please try again. " + remainingAttempts + " remaining attempts");
                remainingAttempts--;
                System.out.println("Please enter the user password: ");
                userInputPassword = EncryptionTools.encryptPassword(passwordAttempt[currentPasswordAttempt]);
                System.out.println("Input password: " + userInputPassword);
                System.out.println("Remaining attempts: " + remainingAttempts);
                System.out.println("Current password attempt: " + currentPasswordAttempt);
                currentPasswordAttempt++;
            }while(remainingAttempts > 0 && userInputPassword.compareTo(loginCustomer.password)!=0);

        } catch (Exception e) {
            message = "Invalid email address. User does not exist.";
        }

        if(userInputPassword.compareTo(loginCustomer.password)!=0)
            message = "Invalid password. No attempts remaining.";

        System.out.println(message);

        Assert.assertEquals(loginCustomer.password,userInputPassword);

    }
}
