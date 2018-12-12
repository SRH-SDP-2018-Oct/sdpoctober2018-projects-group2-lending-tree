package org.lendingtree.project;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.Scanner;

public class ProfileSettingsDatabase {

    public static String Userchoice;
    public static String NewPassword;
    public static String Confirm_Password;
    public static String Newemail;
    public static String New_Address;

    private static Connection databaseConnection = ConnectMSSQLServer.getConnection();


    public static void getCustomerSettings(int customerID) throws SQLException {

                Scanner input = new Scanner(System.in);

                int flag;
                do {
                    System.out.println("press 1 to change password, press 2 to change email id");
                    Userchoice = input.next();
                    flag = 0;
                    switch (Userchoice) {

                        case "1":
                            System.out.println("Please Enter New Password :");
                            NewPassword = input.next();
                            System.out.println("Please Re-Enter New Password :");
                            Confirm_Password = input.next();
                            if (NewPassword.equals(Confirm_Password)) {
                                String query1 = "Update dbo.customer set customer_password=?" + " Where customer_id=" + customerID;

                                PreparedStatement statement1 = databaseConnection.prepareStatement(query1);
                                statement1.setString(1, NewPassword);
                                statement1.execute();
                                System.out.println("Password Changed Successfully");
                                break;
                            } else {
                                System.out.println("Wrong Entry");
                                break;
                            }
                        case "2":
                            System.out.println("Please Enter New email id :");
                            Newemail = input.next();
                            String query2 = "Update dbo.customer set customer_email=?" + " Where customer_id=" + customerID;
                            PreparedStatement statement2 = databaseConnection.prepareStatement(query2);
                            statement2.setString(1, Newemail);
                            statement2.execute();
                            System.out.println("Email Changed Successfully");


                            break;
                        default:
                            System.out.println("Wrong Entry");
                            flag = 1;
                            break;
                    }

                } while (flag > 0);






    }

    public static void getInstitutionSettings(int institutionID) throws SQLException {

        Scanner input = new Scanner(System.in);

        int flag;
        do {
            System.out.println("press 1 to change Address, press 2 to change email id");
            Userchoice = input.next();
            flag = 0;
            switch (Userchoice) {

                case "1":
                    /*System.out.println("Please Enter New Password :");
                    NewPassword = input.next();
                    System.out.println("Please Re-Enter New Password :");
                    Confirm_Password = input.next();
                    if (NewPassword.equals(Confirm_Password)) {
                        String query1 = "Update dbo.customer set customer_password=?" + " Where customer_id=" + institutionID;

                        PreparedStatement statement1 = databaseConnection.prepareStatement(query1);
                        statement1.setString(1, NewPassword);
                        statement1.execute();
                        System.out.println("Password Changed Successfully");
                        break;
                    } else {
                        System.out.println("Wrong Entry");
                        break;
                    }*/
                    System.out.println("Please Enter New Address id :");
                    New_Address = input.next();
                    String query1 = "Update dbo.institution set institution_address=?" + " Where institution_id=" + institutionID;
                    PreparedStatement statement1 = databaseConnection.prepareStatement(query1);
                    statement1.setString(1, New_Address);
                    statement1.execute();
                    System.out.println("Address Changed Successfully");
                    break;

                case "2":
                    System.out.println("Please Enter New email id :");
                    Newemail = input.next();
                    String query2 = "Update dbo.institution set institution_email=?" + " Where institution_id=" + institutionID;
                    PreparedStatement statement2 = databaseConnection.prepareStatement(query2);
                    statement2.setString(1, Newemail);
                    statement2.execute();
                    System.out.println("Email Changed Successfully");


                    break;
                default:
                    System.out.println("Wrong Entry");
                    flag = 1;
                    break;
            }

        } while (flag > 0);

    }
    public static void updatePassword(int userId, String newPassword) throws SQLException {
        String query1 = "Update dbo.customer set customer_password=?" + " Where customer_id=" + userId;

        PreparedStatement statement1 = databaseConnection.prepareStatement(query1);
        statement1.setString(1, newPassword);
        statement1.execute();
        System.out.println("Password Changed Successfully");
    }

    public static void updateEmail(int userID, String newEmail) throws SQLException {
        String query2 = "Update dbo.customer set customer_email=?" + " Where customer_id=" + userID;
        PreparedStatement statement2 = databaseConnection.prepareStatement(query2);
        statement2.setString(1, newEmail);
        statement2.execute();
        System.out.println("Email Changed Successfully");
    }

    public static void updateRepresentativePassword (int userId, String newPassword) throws SQLException {
            String query1 = "Update dbo.customer set customer_password=?" + " Where _id=" + userId;

            PreparedStatement statement1 = databaseConnection.prepareStatement(query1);
            statement1.setString(1, NewPassword);
            statement1.execute();
            System.out.println("Password Changed Successfully");
        }
    public static void updateRepresentativeAddress(int userId , String newAddress)  throws SQLException {

            String query1 = "Update dbo.representative set representative_address=?" + " Where representative_id=" + userId;
            PreparedStatement statement1 = databaseConnection.prepareStatement(query1);
            statement1.setString(1, newAddress);
            statement1.execute();
            System.out.println("Address Changed Successfully");
        }
    public static void updateRepresentativeEmail(int userId , String newEmail) throws SQLException {
                String query2 = "Update dbo.representative set representative_email=?" + " Where representative_id=" + userId;
                PreparedStatement statement2 = databaseConnection.prepareStatement(query2);
                statement2.setString(1, newEmail);
                statement2.execute();
                System.out.println("Email Changed Successfully");
            }
    }

