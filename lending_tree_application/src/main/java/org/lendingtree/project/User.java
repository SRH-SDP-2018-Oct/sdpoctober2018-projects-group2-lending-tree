package org.lendingtree.project;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;

    public int getId(){
        return this.id;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPhone(){
        return this.phone;
    }

    public String getPassword(){
        return this.password;
    }

    public void setId(int newId){
        this.id = newId;
    }

    public void setFirstName(String newFirstName){
        this.firstName = newFirstName;
    }

    public void setLastName(String newLastName){
        this.lastName = newLastName;
    }

    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    public void setPhone(String newPhone){
        this.phone = newPhone;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    public void register() throws SQLException{
        Scanner userInput = new Scanner(System.in);
        String passwordConfirmation = " ";

        lastName = getUserInput("Last Name:");
        firstName = getUserInput("First Name:");
        email = getUserInput("Email:");
        do{
            phone = getUserInput("phone:");
        }while(!isValidEmailAddress(phone));

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
    }

    public abstract User login() throws SQLException;

    protected String getUserInput(String field){
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do{
            System.out.println("Please enter your " + field + ":");
            userInput = scanner.nextLine();
        }while (!confirmUserInputString(userInput));
        return userInput;
    }

    protected Boolean confirmUserInputString(String input){
        char userConfirmation;
        Scanner userInput = new Scanner(System.in);
        do{
            System.out.println("You entered: " + input);
            System.out.println("Is this correct? Y/N");
            userConfirmation = userInput.next().charAt(0);
        } while (userConfirmation != 'Y');
        return true;
    }

    private static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            result = false;
            System.out.println(email + " is not a valid email address, please try again");
        }
        return result;
    }

}