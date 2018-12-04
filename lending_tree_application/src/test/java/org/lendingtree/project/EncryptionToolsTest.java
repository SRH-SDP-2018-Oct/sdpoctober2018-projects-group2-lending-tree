package org.lendingtree.project;

import org.junit.Assert;
import org.junit.Test;

public class EncryptionToolsTest {
    @Test
    public void passwordEncryptionTest(){
        String correctPassword = "Administrator123";
        String slightlyIncorrectPassword = "administrator123";
        String incorrectPassword = "wrong321";
        String simulatedUserInput;

        try {
            System.out.println("Input your secret password : ");
            System.out.println(correctPassword);
            simulatedUserInput = correctPassword;
            String correctHash = EncryptionTools.encryptPassword(simulatedUserInput);
            System.out.println("the computed hash (hex string) : " + correctHash);

            String inputHash;

            System.out.println("Now try to enter a password : " );
            System.out.println(incorrectPassword);
            simulatedUserInput = incorrectPassword;
            inputHash = EncryptionTools.encryptPassword(simulatedUserInput);
            System.out.println("the computed hash (hex string) : " + inputHash);
            System.out.println("Wrong, try again...!");

            System.out.println("Now try to enter a password : " );
            System.out.println(slightlyIncorrectPassword);
            simulatedUserInput = slightlyIncorrectPassword;
            inputHash = EncryptionTools.encryptPassword(simulatedUserInput);
            System.out.println("the computed hash (hex string) : " + inputHash);
            System.out.println("Wrong, try again...!");

            System.out.println("Now try to enter a password : " );
            System.out.println(correctPassword);
            simulatedUserInput = correctPassword;
            inputHash = EncryptionTools.encryptPassword(simulatedUserInput);
            System.out.println("the computed hash (hex string) : " + inputHash);
            if (correctHash.equals(inputHash)){
                System.out.println("You got it!");
            }
            Assert.assertEquals(correctHash, inputHash);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
