package org.lendingtree.project;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static org.lendingtree.project.PaymentHistoryDatabase.databaseConnection;

public class ProfileSettings {
    public static int userChoice;
    public static String newPassword;
    public static String confirmPassword;
    public static String newEmail;
    public static String newAddress;

    public static int getUserChoice() {
        return userChoice;
    }

    public static String getNewPassword() {
        return newPassword;
    }

    public static void setNewPassword(String newPassword) {
        ProfileSettings.newPassword = newPassword;
    }

    public static String getConfirmPassword() {
        return confirmPassword;
    }

    public static void setConfirmPassword(String confirmPassword) {
        ProfileSettings.confirmPassword = confirmPassword;
    }

    public static String getNewEmail() {
        return newEmail;
    }

    public static void setNewEmail(String newEmail) {
        ProfileSettings.newEmail = newEmail;
    }

    public static String getNewAddress() {
        return newAddress;
    }

    public static void setNewAddress(String newAddress) {
        ProfileSettings.newAddress = newAddress;
    }


    public static void getSettings(int userId, String userType) throws SQLException {

        Scanner input = new Scanner(System.in);

        switch (userType) {
            case (App.USER_TYPE_CUSTOMER):
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

            case (App.USER_TYPE_REPRESENTATIVE):
                do {
                    System.out.println("press 1 to change Address, press 2 to change email id");
                    userChoice = input.nextInt();
                    flag = 0;
                    switch (userChoice) {
                        case 1:
                            System.out.println("Please Enter New Password :");
                            newPassword = input.next();
                            System.out.println("Please Re-Enter New Password :");
                            confirmPassword = input.next();
                            if (newPassword.equals(confirmPassword)) {
                                ProfileSettingsDatabase.updateRepresentativePassword(userId, newPassword);
                                break;
                            } else {
                                System.out.println("Wrong Entry");
                                break;
                            }
                        case 2:
                            System.out.println("Please Enter New Address id :");
                            newAddress = input.next();
                            ProfileSettingsDatabase.updateRepresentativeAddress(userId, newAddress);
                            break;

                        case 3:
                            System.out.println("Please Enter New email id :");
                            newEmail = input.next();
                            ProfileSettingsDatabase.updateRepresentativeEmail(userId, newEmail)
                            ;


                            break;
                        default:
                            System.out.println("Wrong Entry");
                            flag = 1;
                            break;
                    }

                } while (flag > 0);
        }
    }
}


