package org.lendingtree.project;

import java.sql.SQLException;
import java.util.Scanner;



public class CustomerProfileSettingTest {

    public static int userChoice;
    public static String newPassword;
    public static String confirmPassword;
    public static String newEmail;
    public static void main(String args[]) throws SQLException {

        int userId ;

        Scanner input = new Scanner(System.in);
        System.out.println("Please provide Customer Id :" );
        userId = input.nextInt();

        int flag;
        do {
            System.out.println("press 1 to change password, press 2 to change email id");
            userChoice = input.nextInt();
            flag = 0;
            switch (userChoice) {

                case 1:
                    System.out.println("Please Enter New Password :");
                    newPassword = input.next();
                    System.out.println("Please Re-Enter New Password :");
                    confirmPassword = input.next();
                    if (newPassword.equals(confirmPassword)) {
                        ProfileSettingsDatabase.updatePassword(userId, newPassword);
                        break;
                    } else {
                        System.out.println("Wrong Entry");

                        break;
                    }
                case 2:
                    System.out.println("Please enter new email id :");
                    newEmail = input.next();
                    ProfileSettingsDatabase.updateEmail(userId, newEmail);
                    break;
                default:
                    System.out.println("Wrong Entry");
                    flag = 1;
                    break;
            }

        } while (flag > 0);
    }
}