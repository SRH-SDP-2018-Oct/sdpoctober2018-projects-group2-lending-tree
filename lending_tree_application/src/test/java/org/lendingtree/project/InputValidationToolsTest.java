package org.lendingtree.project;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

public class InputValidationToolsTest {
    private static final Pattern REGEX_NAME_VALIDATION = Pattern.compile("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");
    private static final Pattern REGEX_NUMBER_VALIDATION = Pattern.compile("\\d+");
    private static final Pattern REGEX_POSTAL_VALIDATION = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$");
    private static final Pattern REGEX_EMAIL_VALIDATION = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final String STRING_SEPARATOR = ", ";

    @Test
    public void testInputOk(){
        String email = "asd@asd.com";
        String addressName = "Los Militares";
        String addressNumber = "5934";
        String addressPostal = "69124";
        String addressCity = "Santiago";
        String addressCountry = "Chile";

        System.out.println(inputEmailTest(email));
        System.out.println(inputAddressTest(addressName, addressNumber, addressPostal, addressCity, addressCountry));

        Assert.assertEquals(email, inputEmailTest(email));
        Assert.assertEquals("Los Militares 5934, 69124, Santiago, Chile", inputAddressTest(addressName, addressNumber, addressPostal, addressCity, addressCountry));
    }

    @Test
    public void testInputNotOkEmail(){
        String emailNOTOK = "asdasd";
        String emailOK = "asd@asd.com";
        String addressName = "Los Militares";
        String addressNumber = "5934";
        String addressPostal = "69124";
        String addressCity = "Santiago";
        String addressCountry = "Chile";

        System.out.println(getUserInputAfterFailTest("Email", REGEX_EMAIL_VALIDATION, emailNOTOK, emailOK, 'Y'));
        System.out.println(inputAddressTest(addressName, addressNumber, addressPostal, addressCity, addressCountry));

        Assert.assertEquals(emailOK, inputEmailTest(emailOK));
        Assert.assertEquals("Los Militares 5934, 69124, Santiago, Chile", inputAddressTest(addressName, addressNumber, addressPostal, addressCity, addressCountry));
    }

    @Test
    public void testInputNotOkAddress(){
        String email = "asdasd@asd.com";
        String addressName = "Los Militares";
        String addressNumberNOTOK = "5934asd";
        String addressNumberOK = "5934";
        String addressPostal = "69124";
        String addressCity = "Santiago";
        String addressCountry = "Chile";

        System.out.println(inputEmailTest(email));
        String fullAddress;

        fullAddress = getUserInputTest("Street Name: ", REGEX_NAME_VALIDATION, addressName, 'Y');
        fullAddress = fullAddress + " " + getUserInputAfterFailTest("Street Number: ", REGEX_NUMBER_VALIDATION, addressNumberNOTOK, addressNumberOK, 'Y');
        fullAddress = fullAddress + STRING_SEPARATOR + getUserInputTest("Postal Code: ", REGEX_POSTAL_VALIDATION, addressPostal, 'Y');
        fullAddress = fullAddress + STRING_SEPARATOR + getUserInputTest("City: ", REGEX_NAME_VALIDATION, addressCity, 'Y');
        fullAddress = fullAddress + STRING_SEPARATOR + getUserInputTest("Country: ", REGEX_NAME_VALIDATION, addressCountry, 'Y');
        System.out.println(fullAddress);

        Assert.assertEquals(email, inputEmailTest(email));
        Assert.assertEquals("Los Militares 5934, 69124, Santiago, Chile", inputAddressTest(addressName, addressNumberOK, addressPostal, addressCity, addressCountry));
    }

    private static String inputEmailTest(String simulated){
        return getUserInputTest("Email", REGEX_EMAIL_VALIDATION, simulated, 'Y');
    }

    private static String inputAddressTest(String addressName, String addressNumber, String addressPostal, String addressCity, String addressCountry){
        String fullAddress;

        fullAddress = getUserInputTest("Street Name: ", REGEX_NAME_VALIDATION, addressName, 'Y');
        fullAddress = fullAddress + " " + getUserInputTest("Street Number: ", REGEX_NUMBER_VALIDATION, addressNumber, 'Y');
        fullAddress = fullAddress + STRING_SEPARATOR + getUserInputTest("Postal Code: ", REGEX_POSTAL_VALIDATION, addressPostal, 'Y');
        fullAddress = fullAddress + STRING_SEPARATOR + getUserInputTest("City: ", REGEX_NAME_VALIDATION, addressCity, 'Y');
        fullAddress = fullAddress + STRING_SEPARATOR + getUserInputTest("Country: ", REGEX_NAME_VALIDATION, addressCountry, 'Y');

        return fullAddress;
    }

    private static Boolean confirmUserInputStringTest(String input, char simulatedInput){
        char userConfirmation;
        System.out.println("You entered: " + input);
        System.out.println("Is this correct? Y/N");
        userConfirmation = simulatedInput;
        System.out.println(simulatedInput);
        if (userConfirmation == 'Y') return true;
        return false;
    }

    private static String getUserInputTest(String field, Pattern regex, String simulatedInput, char simulatedConfirmation){
        String userInput;
        int numberOfTries = 0;
        do{
            if(numberOfTries > 0){
                System.out.println("Invalid input. " + field + " only accepts regular expressions: " + regex.pattern());
            }
            System.out.println("Please enter your " + field + ":");
            System.out.println(simulatedInput);
            userInput = simulatedInput;
            numberOfTries++;
            System.out.println(regex.matcher(userInput).matches());
        }while (!regex.matcher(userInput).matches() || !confirmUserInputStringTest(userInput, simulatedConfirmation));
        return userInput;
    }

    private static String getUserInputAfterFailTest(String field, Pattern regex, String simulatedInputNOTOK, String simulatedInputOK, char simulatedConfirmation){
        String userInput;
        int numberOfTries = 0;
        do{
            if(numberOfTries > 0){
                System.out.println("Invalid input. " + field + " only accepts regular expressions: " + regex.pattern());
            }
            System.out.println("Please enter your " + field + ":");
            if(numberOfTries == 0) userInput = simulatedInputNOTOK;
            else userInput = simulatedInputOK;
            System.out.println(userInput);
            numberOfTries++;
            System.out.println(regex.matcher(userInput).matches());
        }while (!regex.matcher(userInput).matches() || !confirmUserInputStringTest(userInput, simulatedConfirmation));
        return userInput;
    }
}
