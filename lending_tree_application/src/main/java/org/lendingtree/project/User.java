package org.lendingtree.project;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private static final Pattern REGEX_PHONE_VALIDATION = Pattern.compile("^(?=(?:[8-9]){1})(?=[0-9]{8}).*");
    private static final Pattern REGEX_NAME_VALIDATION = Pattern.compile("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");

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

        lastName = InputValidationTools.getUserInput("Last Name:", REGEX_NAME_VALIDATION);
        firstName = InputValidationTools.getUserInput("First Name:", REGEX_NAME_VALIDATION);
        phone = InputValidationTools.getUserInput("Phone:", REGEX_PHONE_VALIDATION);

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

}