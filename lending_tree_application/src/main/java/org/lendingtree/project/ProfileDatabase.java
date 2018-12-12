package org.lendingtree.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ProfileDatabase {

    private static final String TABLE_CUSTOMER = "customer";
    private static final String TABLE_CUSTOMER_TYPE = "customer_type";
    private static final String COLUMN_CUSTOMER_ID = "customer_id";
    private static final String COLUMN_CUSTOMER_TYPE_ID = "customer_type_id";
    private static final String COLUMN_CUSTOMER_LAST_NAME = "customer_last_name";
    private static final String COLUMN_CUSTOMER_FIRST_NAME = "customer_first_name";
    private static final String COLUMN_CUSTOMER_ADDRESS = "customer_address";
    private static final String COLUMN_CUSTOMER_EMAIL = "customer_email";
    private static final String COLUMN_CUSTOMER_PHONE = "customer_phone";
    private static final String COLUMN_CUSTOMER_CURRENT_EXPENSES = "customer_current_expenses";
    private static final String COLUMN_CUSTOMER_PAY_SLIP = "customer_pay_slip";
    private static final String COLUMN_CUSTOMER_TAX_DETAILS = "customer_tax_details";
    private static final String COLUMN_CUSTOMER_IDENTIFICATION_NUMBER = "customer_identification_number";
    private static final String COLUMN_CUSTOMER_TYPE_DESCRIPTION = "customer_type_description";

    private static final String TABLE_REPRESENTATIVE = "representative";
    private static final String TABLE_INSTITUTION = "institution";
    private static final String COLUMN_INSTITUTION_ID = "institution_id";
    private static final String COLUMN_REPRESENTATIVE_ID = "representative_id";
    private static final String COLUMN_REPRESENTATIVE_LAST_NAME = "representative_last_name";
    private static final String COLUMN_REPRESENTATIVE_FIRST_NAME = "representative_first_name";
    private static final String COLUMN_REPRESENTATIVE_EMAIL = "representative_email";
    private static final String COLUMN_REPRESENTATIVE_PHONE = "representative_phone";



    private static Connection databaseConnection = ConnectMSSQLServer.getConnection();

    public static void getCustomerProfile(int profileID) throws SQLException {

        PreparedStatement preparedStatement;
        String query = "SELECT * FROM " + TABLE_CUSTOMER +
                " INNER JOIN " + TABLE_CUSTOMER_TYPE +
                " ON " + TABLE_CUSTOMER + "." + COLUMN_CUSTOMER_TYPE_ID + " = " +
                TABLE_CUSTOMER_TYPE + "." + COLUMN_CUSTOMER_TYPE_ID +
                " WHERE " + COLUMN_CUSTOMER_ID + " = ?";


        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setInt(1, profileID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("Customer ID: \t\t\t " + resultSet.getInt(COLUMN_CUSTOMER_ID));
            System.out.println("Customer type: \t\t\t " + resultSet.getString(COLUMN_CUSTOMER_TYPE_DESCRIPTION));
            System.out.println("Name: \t\t\t\t\t " + resultSet.getString(COLUMN_CUSTOMER_FIRST_NAME) +
                 " " + resultSet.getString(COLUMN_CUSTOMER_LAST_NAME));
            System.out.println("Address: \t\t\t\t " + resultSet.getString(COLUMN_CUSTOMER_ADDRESS ) );
            System.out.println("Email: \t\t\t\t\t " + resultSet.getString(COLUMN_CUSTOMER_EMAIL));
            System.out.println("Phone number: \t\t\t " + resultSet.getString(COLUMN_CUSTOMER_PHONE));

            if (resultSet.getBoolean(COLUMN_CUSTOMER_CURRENT_EXPENSES)) {
                System.out.println("Current expenses: \t\t yes");
            } else {
                System.out.println("Current expenses: \t\t no");
            }
            if (resultSet.getBoolean(COLUMN_CUSTOMER_PAY_SLIP)){
                System.out.println("Pay slip: \t\t\t\t yes");
            } else {
                System.out.println("Pay slip: \t\t\t\t no");
            }
            if (resultSet.getBoolean(COLUMN_CUSTOMER_TAX_DETAILS)){
                System.out.println("Tax details: \t\t\t yes");
            } else {
                System.out.println("Tax details: \t\t\t no");
            }
            System.out.println("Identification number: \t " + resultSet.getString(COLUMN_CUSTOMER_IDENTIFICATION_NUMBER));
        } else {
            System.out.println("No Data Found!! User not Registered");
        }
    }

    public static void getRepresentativeProfile(int profileID) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "SELECT * FROM " + TABLE_REPRESENTATIVE +
                " INNER JOIN " + TABLE_INSTITUTION +
                " ON " + TABLE_REPRESENTATIVE + "." + COLUMN_INSTITUTION_ID + " = " +
                TABLE_INSTITUTION + "." + COLUMN_INSTITUTION_ID +
                " WHERE " + COLUMN_REPRESENTATIVE_ID + " = ?";

        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setInt(1, profileID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("Representative ID: \t\t " + resultSet.getInt(COLUMN_REPRESENTATIVE_ID));
            System.out.println("Name: \t\t\t\t\t " + resultSet.getString(COLUMN_REPRESENTATIVE_FIRST_NAME) +
                    " " + resultSet.getString(COLUMN_REPRESENTATIVE_LAST_NAME));
            System.out.println("Email: \t\t\t\t\t " + resultSet.getString(COLUMN_REPRESENTATIVE_EMAIL));
            System.out.println("Phone: \t\t\t\t\t " + resultSet.getString(COLUMN_REPRESENTATIVE_PHONE));

        } else {
            System.out.println("No Data Found!! Institution not Registered");
        }
    }
}
