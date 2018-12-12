package org.lendingtree.project;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;


public abstract class ProfileSettingsDatabase {


    private static Connection databaseConnection = ConnectMSSQLServer.getConnection();


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
            String query1 = "Update dbo.representative set representative_password=?" + " Where representative_id=" + userId;

            PreparedStatement statement1 = databaseConnection.prepareStatement(query1);
            statement1.setString(1, newPassword);
            statement1.execute();
            System.out.println("Password Changed Successfully");
        }

    public static void updateRepresentativeEmail(int userId , String newEmail) throws SQLException {
                String query2 = "Update dbo.representative set representative_email=?" + " Where representative_id=" + userId;
                PreparedStatement statement2 = databaseConnection.prepareStatement(query2);
                statement2.setString(1, newEmail);
                statement2.execute();
                System.out.println("Email Changed Successfully");
            }
    }

