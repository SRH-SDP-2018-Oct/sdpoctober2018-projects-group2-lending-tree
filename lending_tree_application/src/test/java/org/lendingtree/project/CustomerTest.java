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
        Double rating = 15.18;
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
            newCustomer.setTaxDetails(taxDetails);
            newCustomer.setPaySlip(paySlip);
            newCustomer.setCurrentExpenses(currentExpenses);

            int maxCustomerTypeValue;
            String passwordConfirmation;

            do{
                System.out.println("Please enter your Last Name: ");
                newCustomer.setLastName(lastName);
            }while (!confirmUserInputString(newCustomer.getLastName()));

            do{
                System.out.println("Please enter your First Name: ");
                newCustomer.setFirstName(firstName);
            }while (!confirmUserInputString(newCustomer.getFirstName()));

            maxCustomerTypeValue = CustomerDatabase.listCustomerTypes();
            System.out.println("Maximum id of Customer Type is: " + maxCustomerTypeValue);

            newCustomer.setCustomerTypeId(customerTypeId);

            newCustomer.setCustomerType(CustomerDatabase.getCustomerType(newCustomer.getCustomerTypeId()));
            System.out.println("You selected: " + newCustomer.getCustomerType());

            do{
                System.out.println("Please enter your email address: ");
                newCustomer.setEmail(email);
                System.out.println(newCustomer.getEmail());

                if(CustomerDatabase.checkEmail(newCustomer.getEmail())) System.out.println("Mail exists in the database");
                else System.out.println("Mail does not exists in the database");

                while(CustomerDatabase.checkEmail(newCustomer.getEmail())){
                    System.out.println("Email already in use, please enter a new one: ");
                    newCustomer.setEmail("other@mail.com");
                    System.out.println("other@mail.com");
                }

            }while (!confirmUserInputString(newCustomer.getEmail()));

            do{
                System.out.println("Please enter your home address: ");
                newCustomer.setAddress(address);
            }while (!confirmUserInputString(newCustomer.getAddress()));

            do{
                System.out.println("Please enter your phone number: ");
                newCustomer.setPhone(phone);
            }while (!confirmUserInputString(newCustomer.getPhone()));

            do{
                System.out.println("Please enter your national identification number: ");
                newCustomer.setIdentificationNumber(identificationNumber);
            }while (!confirmUserInputString(newCustomer.getIdentificationNumber()));

            System.out.println("Please select a valid number for the customer rating: ");

            newCustomer.setRating(rating);

            System.out.println("Your customer rating is: " + newCustomer.getRating());

            do{
                System.out.println("Please enter your password: ");
                newCustomer.setPassword(EncryptionTools.encryptPassword(password));
                System.out.println("Please enter your password again to confirm: ");
                passwordConfirmation = EncryptionTools.encryptPassword(password);
            }while (newCustomer.getPassword().compareTo(passwordConfirmation) != 0);

            id = CustomerDatabase.insert(newCustomer);

            Assert.assertEquals(firstName, newCustomer.getFirstName());
            Assert.assertEquals(lastName, newCustomer.getLastName());
            Assert.assertEquals(EncryptionTools.encryptPassword(password), newCustomer.getPassword());
            // Assert.assertEquals(rating, newCustomer.getRating);
            System.out.println("Input: " + rating + " --- Customer: " + newCustomer.getRating());
            Assert.assertEquals(identificationNumber, newCustomer.getIdentificationNumber());
            Assert.assertEquals(taxDetails, newCustomer.getTaxDetails());
            Assert.assertEquals(paySlip, newCustomer.getPaySlip());
            Assert.assertEquals(currentExpenses, newCustomer.getCurrentExpenses());
            Assert.assertEquals(phone, newCustomer.getPhone());
            Assert.assertEquals(address, newCustomer.getAddress());
            Assert.assertEquals(customerType, newCustomer.getCustomerType());
            Assert.assertEquals(customerTypeId, newCustomer.getCustomerTypeId());
            Assert.assertEquals(email, newCustomer.getEmail());
            Assert.assertEquals(id, newCustomer.getId());
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
        loginCustomer.setId(0);

        System.out.println("Please enter the user email: ");
        loginCustomer.setEmail(incorrectEmail);
        System.out.println(loginCustomer.getEmail());
        try {
            loginCustomer = CustomerDatabase.getCustomer(loginCustomer.getEmail());
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
            }while(remainingAttempts > 0 && userInputPassword.compareTo(loginCustomer.getPassword())!=0);

        } catch (Exception e) {
            message = "Invalid user. Email not found";
        }

        System.out.println(message);

        System.out.println("===================== NEW SCENARIO ==================");
        remainingAttempts = 3;
        currentPasswordAttempt = 0;
        message = "Login successful.";
        loginCustomer.setEmail(correctEmail);
        System.out.println(loginCustomer.getEmail());
        try {
            loginCustomer = CustomerDatabase.getCustomer(loginCustomer.getEmail());
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
            }while(remainingAttempts > 0 && userInputPassword.compareTo(loginCustomer.getPassword())!=0);

        } catch (Exception e) {
            message = "Invalid email address. User does not exist.";
        }

        if(userInputPassword.compareTo(loginCustomer.getPassword())!=0)
            message = "Invalid password. No attempts remaining.";

        System.out.println(message);

        System.out.println("===================== NEW SCENARIO ==================");
        remainingAttempts = 4;
        currentPasswordAttempt = 0;
        message = "Login successful.";
        loginCustomer.setEmail(correctEmail);
        System.out.println(loginCustomer.getEmail());
        try {
            loginCustomer = CustomerDatabase.getCustomer(loginCustomer.getEmail());
            System.out.println(loginCustomer.getFirstName());
            System.out.println(loginCustomer.getLastName());
            System.out.println(loginCustomer.getPassword());
            do{
                if(remainingAttempts < 4)
                    System.out.println("Invalid password, please try again. " + remainingAttempts + " remaining attempts");
                remainingAttempts--;
                System.out.println("Please enter the user password: ");
                userInputPassword = EncryptionTools.encryptPassword(passwordAttempt[currentPasswordAttempt]);
                System.out.println("Input password: " + userInputPassword);
                System.out.println("Remaining attempts: " + remainingAttempts);
                System.out.println("Current password attempt: " + currentPasswordAttempt);
                currentPasswordAttempt++;
            }while(remainingAttempts > 0 && userInputPassword.compareTo(loginCustomer.getPassword())!=0);

        } catch (Exception e) {
            message = "Invalid email address. User does not exist.";
        }

        if(userInputPassword.compareTo(loginCustomer.getPassword())!=0)
            message = "Invalid password. No attempts remaining.";

        System.out.println(message);

        Assert.assertEquals(loginCustomer.getPassword(),userInputPassword);

    }
}
