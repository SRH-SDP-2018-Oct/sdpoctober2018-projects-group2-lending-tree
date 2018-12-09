package org.lendingtree.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDatabase {

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
    //private static final String COLUMN_CUSTOMER_RATING = "customer_rating";
    private static final String COLUMN_CUSTOMER_TYPE_DESCRIPTION = "customer_type_description";

    private static final String TABLE_INSTITUTION = "institution";
    private static final String TABLE_INSTITUTION_TYPE = "institution_type";
    private static final String COLUMN_INSTITUTION_ID = "institution_id";
    private static final String COLUMN_INSTITUTION_TYPE_ID = "institution_type_id";
    private static final String COLUMN_INSTITUTION_NAME = "institution_name";
    private static final String COLUMN_INSTITUTION_ADDRESS = "institution_address";
    private static final String COLUMN_INSTITUTION_EMAIL = "institution_email";
    private static final String COLUMN_INSTITUTION_FINANCIAL_STATUS = "institution_financial_status";
    private static final String COLUMN_INSTITUTION_RATING = "institution_rating";
    private static final String COLUMN_INSTITUTION_TYPE_DESCRIPTION = "institution_type_description";


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
            //System.out.println("Customer Rating: " + resultSet.getDouble(COLUMN_CUSTOMER_RATING);
        } else {
            System.out.println("No Data Found!! User not Registered");
        }
    }

    public static void getInstitutionProfile(int profileID) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "SELECT * FROM " + TABLE_INSTITUTION +
                " INNER JOIN " + TABLE_INSTITUTION_TYPE +
                " ON " + TABLE_INSTITUTION + "." + COLUMN_INSTITUTION_TYPE_ID + " = " +
                TABLE_INSTITUTION_TYPE + "." + COLUMN_INSTITUTION_TYPE_ID +
                " WHERE " + COLUMN_INSTITUTION_ID + " = ?";

        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setInt(1, profileID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("Institution ID: \t\t\t\t\t\t " + resultSet.getInt(COLUMN_INSTITUTION_ID));
            System.out.println("Institution type: \t\t\t\t\t\t " + resultSet.getString(COLUMN_INSTITUTION_TYPE_DESCRIPTION  ));
            System.out.println("Institution Name: \t\t\t\t\t\t " + resultSet.getString(COLUMN_INSTITUTION_NAME)) ;
            System.out.println("Address: \t\t\t\t\t\t\t\t " + resultSet.getString(COLUMN_INSTITUTION_ADDRESS ) );
            System.out.println("Email: \t\t\t\t\t\t\t\t\t " + resultSet.getString(COLUMN_INSTITUTION_EMAIL));
            System.out.println("Institution Financial Status: \t\t\t " + resultSet.getString(COLUMN_INSTITUTION_FINANCIAL_STATUS));
            System.out.println("Institution rating : \t\t\t\t\t " + resultSet.getDouble(COLUMN_INSTITUTION_RATING));


        } else {
            System.out.println("No Data Found!! Institution not Registered");
        }
    }
}
