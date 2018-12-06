package org.lendingtree.project;

import java.sql.SQLException;
import java.util.Scanner;

public class Customer extends User {
    private String customerType;
    private int customerTypeId = 0;
    private String address;
    private int currentExpenses = 0;
    private int paySlip = 0;
    private int taxDetails = 0;
    private String identificationNumber;
    private float rating;

    public String getCustomerType(){
        return this.customerType;
    }

    public int getCustomerTypeId(){
        return this.customerTypeId;
    }

    public String getAddress(){
        return this.address;
    }

    public int getCurrentExpenses(){
        return this.currentExpenses;
    }

    public int getPaySlip(){
        return this.paySlip;
    }

    public int getTaxDetails(){
        return this.taxDetails;
    }

    public String getIdentificationNumber(){
        return this.identificationNumber;
    }

    public Float getRating(){
        return this.rating;
    }

    public void setCustomerType(String newCustomerType){
        this.customerType = newCustomerType;
    }

    public void setCustomerTypeId(int newCustomerTypeId){
        this.customerTypeId = newCustomerTypeId;
    }

    public void setAddress(String newAddress){
        this.address = newAddress;
    }

    public void setCurrentExpenses(int newCurrentExpenses){
        this.currentExpenses = newCurrentExpenses;
    }

    public void setPaySlip(int newPaySlip){
        this.paySlip = newPaySlip;
    }

    public void setTaxDetails(int newTaxDetails){
        this.taxDetails = newTaxDetails;
    }

    public void setIdentificationNumber(String newIdentificationNumber){
        this.identificationNumber = newIdentificationNumber;
    }

    public void setRating(Float newRating){
        this.rating = newRating;
    }


    @Override
    public void register() throws SQLException{
        Scanner userInput = new Scanner(System.in);
        int maxCustomerTypeValue;
        String passwordConfirmation = "";

        do{
            System.out.println("Please enter your Last Name: ");
            this.setLastName(userInput.nextLine());
        }while (!confirmUserInputString(this.lastName));

        do{
            System.out.println("Please enter your First Name: ");
            this.setFirstName(userInput.nextLine());
        }while (!confirmUserInputString(this.firstName));

        maxCustomerTypeValue = CustomerDatabase.listCustomerTypes();

        do{
            System.out.println("Please select a valid number for the customer type: ");
            while(!userInput.hasNextInt()) {
                System.out.println("That is not a valid selection");
                userInput.next();
            }
            customerTypeId = userInput.nextInt();
        } while(customerTypeId < 1 || customerTypeId > maxCustomerTypeValue);

        customerType = CustomerDatabase.getCustomerType(customerTypeId);
        System.out.println("You selected: " + customerType);

        do{
            System.out.println("Please enter your email address: ");
            this.setEmail(userInput.nextLine());

            while (CustomerDatabase.checkEmail(this.email)){
                System.out.println("Email already in use, please enter a new one: ");
                this.setEmail(userInput.nextLine());
            }

        }while (!confirmUserInputString(this.email));

        do{
            System.out.println("Please enter your home address: ");
            this.address = userInput.nextLine();
        }while (!confirmUserInputString(this.address));

        do{
            System.out.println("Please enter your phone number: ");
            this.setPhone(userInput.nextLine());
        }while (!confirmUserInputString(this.phone));

        do{
            System.out.println("Please enter your national identification number: ");
            this.identificationNumber = userInput.nextLine();
        }while (!confirmUserInputString(this.identificationNumber));

        do{
            System.out.println("Please select a valid number for the customer rating: ");
            while(!userInput.hasNextFloat()) {
                System.out.println("That is not a valid selection");
                userInput.next();
            }
            this.rating = userInput.nextFloat();
        } while(this.rating < 0);
        System.out.println("Your customer rating is: " + this.rating);

        do{
            try{
                System.out.println("Please enter your password: ");
                this.setPassword(EncryptionTools.encryptPassword(userInput.nextLine()));
                System.out.println("Please enter your password again to confirm: ");
                passwordConfirmation = EncryptionTools.encryptPassword(userInput.nextLine());
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }while (this.getPassword().compareTo(passwordConfirmation) != 0);

        CustomerDatabase.insert(this);
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

    @Override
    public User login() {
        Scanner userInput = new Scanner(System.in);
        String message = "Login successful.";
        Customer loginCustomer = new Customer();
        int remainingAttempts = 3;
        String userInputPassword = new String();

        System.out.println("Please enter the user email: ");
        loginCustomer.setEmail(userInput.nextLine());
        try {
            loginCustomer = CustomerDatabase.getCustomer(loginCustomer.getEmail());
            do{
                if(remainingAttempts < 3)
                    System.out.println("Invalid password, please try again. " + remainingAttempts + " remaining attempts");
                remainingAttempts--;
                System.out.println("Please enter the user password: ");
                userInputPassword = EncryptionTools.encryptPassword(userInput.nextLine());
            }while(remainingAttempts > 0 && userInputPassword.compareTo(loginCustomer.getPassword())!=0);

        } catch (Exception e) {
            message = "Invalid email address. User does not exist.";
            System.out.println(message);
            return null;
        }

        if(userInputPassword.compareTo(loginCustomer.getPassword())!=0)
            message = "Invalid password. No attempts remaining.";

        System.out.println(message);
        return  loginCustomer;
    }

}
