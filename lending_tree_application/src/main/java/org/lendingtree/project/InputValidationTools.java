package org.lendingtree.project;

import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class InputValidationTools {
    private static final Pattern REGEX_NAME_VALIDATION = Pattern.compile("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");
    private static final Pattern REGEX_NUMBER_VALIDATION = Pattern.compile("^[0-9]{1,10}$");
    private static final Pattern REGEX_POSTAL_VALIDATION = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$");
    private static final Pattern REGEX_EMAIL_VALIDATION = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern REGEX_PHONE_VALIDATION = Pattern.compile("^[0-9]{7,10}$");
    private static final Pattern REGEX_IDENTIFICATION_NUMBER = Pattern.compile("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}[0-9]{0,8}$");
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

    protected static int inputNumber(){
        return Integer.parseInt(getUserInput("selection", REGEX_NUMBER_VALIDATION));
    }

    protected static String inputAddress(){
        String fullAddress;

        fullAddress = getUserInput("Street Name", REGEX_NAME_VALIDATION);
        fullAddress = fullAddress + " " + getUserInput("Street Number", REGEX_NUMBER_VALIDATION);
        fullAddress = fullAddress + STRING_SEPARATOR + getUserInput("Postal Code", REGEX_POSTAL_VALIDATION);
        fullAddress = fullAddress + STRING_SEPARATOR + getUserInput("City", REGEX_NAME_VALIDATION);
        fullAddress = fullAddress + STRING_SEPARATOR + getUserInput("Country", REGEX_NAME_VALIDATION);

        return fullAddress;
    }

    protected static Double inputRating(){
        Scanner userInput = new Scanner(System.in);
        Double rating;

        do{
            System.out.println("Please select a valid number for the rating: ");
            while(!userInput.hasNextFloat()) {
                System.out.println("That is not a valid selection");
                userInput.next();
            }
            rating = userInput.nextDouble();
        } while(rating < 0);
        System.out.println("Your customer rating is: " + rating);
        return rating;
    }

    private static Boolean confirmUserInputString(String input){
        char userConfirmation;
        Scanner userInput = new Scanner(System.in);
        System.out.println("You entered: " + input);
        System.out.println("Is this correct? Y/N");
        userConfirmation = userInput.next().charAt(0);
        if(userConfirmation == 'Y' || userConfirmation == 'y') return true;
        return false;
    }

    protected static int inputNumber(int minValue, int maxValue){
        Scanner scanner = new Scanner(System.in);
        int userInput;
        int numberOfTries = 0;
        do{
            if(numberOfTries > 0){
                System.out.println("Invalid input. Please select a number between " + minValue + " and " + maxValue);
            }
            System.out.println("Please enter your selection:");
            userInput = scanner.nextInt();
            numberOfTries++;
        }while (!scanner.hasNextInt() || userInput < minValue || userInput > maxValue);

        return userInput;
    }

    private static String getUserInput(String field, Pattern regex){
        Scanner scanner = new Scanner(System.in);
        String userInput;
        int numberOfTries = 0;
        do{
            if(numberOfTries > 0){
                System.out.println("Invalid input. " + field + " only accepts regular expressions: " + regex.pattern());
            }
            System.out.println("Please enter your " + field + ":");
            userInput = scanner.nextLine();
            numberOfTries++;
        }while (!regex.matcher(userInput).matches() || !confirmUserInputString(userInput));
        return userInput.toUpperCase();
    }
}
