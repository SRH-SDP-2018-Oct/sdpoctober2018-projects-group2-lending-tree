package org.lendingtree.project;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidationTools {
    private static final Pattern REGEX_NAME_VALIDATION = Pattern.compile("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");
    private static final Pattern REGEX_NUMBER_VALIDATION = Pattern.compile("^\\d");
    private static final Pattern REGEX_POSTAL_VALIDATION = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$");
    private static final Pattern REGEX_EMAIL_VALIDATION = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern REGEX_PHONE_VALIDATION = Pattern.compile("^(?=(?:[8-9]){1})(?=[0-9]{8}).*");
    private static final Pattern REGEX_IDENTIFICATION_NUMBER = Pattern.compile("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");
    private static final Pattern REGEX_FINANCIAL_STATUS = Pattern.compile("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");
    private static final String STRING_SEPARATOR = ", ";

   protected static String inputFinancialStatus(){
       return getUserInput("Institution Financial Status", REGEX_FINANCIAL_STATUS);
   }

   protected static String inputIdentificationNumber(){
       return getUserInput("Identification Number", REGEX_IDENTIFICATION_NUMBER);
   }

   protected  static String inputName(String field){
       return getUserInput(field, REGEX_NAME_VALIDATION);
   }

   protected  static String inputPhone(){
       return getUserInput("Phone", REGEX_PHONE_VALIDATION);
   }

    protected static String inputEmail(){
        return getUserInput("Email", REGEX_EMAIL_VALIDATION);
    }

    protected static String inputAddress(){
        String fullAddress;

        fullAddress = getUserInput("Street Name: ", REGEX_NAME_VALIDATION);
        fullAddress = fullAddress + " " + getUserInput("Street Number: ", REGEX_NUMBER_VALIDATION);
        fullAddress = fullAddress + STRING_SEPARATOR + getUserInput("Postal Code: ", REGEX_POSTAL_VALIDATION);
        fullAddress = fullAddress + STRING_SEPARATOR + getUserInput("City: ", REGEX_NAME_VALIDATION);
        fullAddress = fullAddress + STRING_SEPARATOR + getUserInput("Country: ", REGEX_NAME_VALIDATION);

        return fullAddress;
    }

    protected static Double inputRating(){
        Scanner userInput = new Scanner(System.in);
        Double rating;

        do{
            System.out.println("Please select a valid number for the customer rating: ");
            while(!userInput.hasNextFloat()) {
                System.out.println("That is not a valid selection");
                userInput.next();
            }
            rating = userInput.nextDouble();
        } while(rating < 0);
        System.out.println("Your customer rating is: " + rating);
        return rating;
    }

    protected static Boolean confirmUserInputString(String input){
        char userConfirmation;
        Scanner userInput = new Scanner(System.in);
        do{
            System.out.println("You entered: " + input);
            System.out.println("Is this correct? Y/N");
            userConfirmation = userInput.next().charAt(0);
        } while (userConfirmation != 'Y');
        return true;
    }

    private static String getUserInput(String field, Pattern regex){
        Scanner scanner = new Scanner(System.in);
        String userInput;
        int numberOfTries = 0;
        Matcher matcher;
        do{
            if(numberOfTries > 0){
                System.out.println("Invalid input. " + field + " only accepts regular expressions: " + regex.pattern());
            }
            System.out.println("Please enter your " + field + ":");
            userInput = scanner.nextLine();
            matcher = regex.matcher(userInput);
            numberOfTries++;
        }while (!confirmUserInputString(userInput) || !matcher.find());
        return userInput;
    }
}