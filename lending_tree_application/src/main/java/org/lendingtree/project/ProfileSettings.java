package org.lendingtree.project;

import java.sql.SQLException;
import java.util.Scanner;


public class ProfileSettings {
    public static int userChoice;
    public static String newPassword;
    public static String confirmPassword;
    public static String newEmail;


    public static void getSettings(int userId, String userType) throws SQLException {

        Scanner input = new Scanner(System.in);

        switch (userType) {
            case (App.USER_TYPE_CUSTOMER):
                int flag;
                do {
                    System.out.println("Please select one of the following options:\n" +
                            "1) Change Password\n" +
                            "2) Change Email\n" + "3) Go back\n");
                    userChoice = input.nextInt();
                    flag = 0;
                    switch (userChoice) {

                        case 1:
                            System.out.println("Please Enter New Password :");
                            newPassword = input.next();
                            System.out.println("Please Re-Enter New Password :");
                            confirmPassword = input.next();
                            if (newPassword.equals(confirmPassword)) {
                                ProfileSettingsDatabase.updatePassword(userId, EncryptionTools.encryptPassword(newPassword));
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
                        case 3:
                            break;
                        default:
                            System.out.println("Wrong Entry");
                            flag = 1;
                            break;
                    }

                } while (flag > 0);

            case (App.USER_TYPE_REPRESENTATIVE):
                int flag1;
                do {
                    System.out.println("Please select one of the following options:\n" +
                            "1) Change Password\n" +
                            "2) Change Email\n" + "3)Go back\n"
                              );
                    userChoice = input.nextInt();
                    flag1 = 0;
                    switch (userChoice) {
                        case 1:
                            System.out.println("Please Enter New Password :");
                            newPassword = input.next();
                            System.out.println("Please Re-Enter New Password :");
                            confirmPassword = input.next();
                            if (newPassword.equals(confirmPassword)) {
                                ProfileSettingsDatabase.updateRepresentativePassword(userId, EncryptionTools.encryptPassword(newPassword));
                                break;
                            } else {
                                System.out.println("Wrong Entry");

                                break;
                            }

                        case 2:
                            System.out.println("Please Enter New email id :");
                            newEmail = input.next();
                            ProfileSettingsDatabase.updateRepresentativeEmail(userId, newEmail);
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Wrong Entry");
                            flag1 = 1;
                            break;
                    }

                } while (flag1 > 0);
        }
    }
}


