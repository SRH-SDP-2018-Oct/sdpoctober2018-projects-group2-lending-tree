package org.lendingtree.project;

import java.sql.*;

public class CustomerDatabase {
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
    private static final String COLUMN_CUSTOMER_RATING = "customer_rating";
    private static final String COLUMN_CUSTOMER_PASSWORD = "customer_password";

    private static Connection databaseConnection = ConnectMSSQLServer.getConnection();

    public static int listCustomerTypes() throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        int maxValueCustomerType = 0;
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT * FROM customer_type");
        while (dbResultSet.next()) {
            System.out.println(dbResultSet.getString(1) + ". " + dbResultSet.getString(2));
            maxValueCustomerType++;
        }
        return  maxValueCustomerType;
    }

    public static int getCustomerId(String customerEmail) throws SQLException{
        PreparedStatement dbPreparedStatement;
        String getCustomerIdString = "SELECT " + COLUMN_CUSTOMER_ID + " FROM customer WHERE " + COLUMN_CUSTOMER_EMAIL + " = ?";
        dbPreparedStatement = databaseConnection.prepareStatement(getCustomerIdString);
        dbPreparedStatement.setString(1, customerEmail);
        ResultSet dbResultSet = dbPreparedStatement.executeQuery();
        dbResultSet.next();
        return Integer.parseInt(dbResultSet.getString(1));
    }

    public static String getCustomerType(int searchCustomerTypeId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_type_description FROM customer_type WHERE " + COLUMN_CUSTOMER_TYPE_ID + " = " + searchCustomerTypeId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }

    public static Customer getCustomer(String customerEmail) throws SQLException{
        Customer customerFromDb = new Customer();

        customerFromDb.setId(getCustomerId(customerEmail));
        customerFromDb.setCustomerTypeId(Integer.parseInt(getCustomerColumnValue(COLUMN_CUSTOMER_TYPE_ID, customerFromDb.getId())));
        customerFromDb.setCustomerType(getCustomerType(customerFromDb.getCustomerTypeId()));
        customerFromDb.setLastName(getCustomerColumnValue(COLUMN_CUSTOMER_LAST_NAME, customerFromDb.getId()));
        customerFromDb.setFirstName(getCustomerColumnValue(COLUMN_CUSTOMER_FIRST_NAME, customerFromDb.getId()));
        customerFromDb.setAddress(getCustomerColumnValue(COLUMN_CUSTOMER_ADDRESS, customerFromDb.getId()));
        customerFromDb.setEmail(customerEmail);
        customerFromDb.setPhone(getCustomerColumnValue(COLUMN_CUSTOMER_PHONE, customerFromDb.getId()));
        customerFromDb.setCurrentExpenses(Integer.parseInt(getCustomerColumnValue(COLUMN_CUSTOMER_CURRENT_EXPENSES, customerFromDb.getId())));
        customerFromDb.setPaySlip(Integer.parseInt(getCustomerColumnValue(COLUMN_CUSTOMER_PAY_SLIP, customerFromDb.getId())));
        customerFromDb.setTaxDetails(Integer.parseInt(getCustomerColumnValue(COLUMN_CUSTOMER_TAX_DETAILS, customerFromDb.getId())));
        customerFromDb.setIdentificationNumber(getCustomerColumnValue(COLUMN_CUSTOMER_IDENTIFICATION_NUMBER, customerFromDb.getId()));
        customerFromDb.setRating(Float.parseFloat(getCustomerColumnValue(COLUMN_CUSTOMER_RATING, customerFromDb.getId())));
        customerFromDb.setPassword(getCustomerColumnValue(COLUMN_CUSTOMER_PASSWORD, customerFromDb.getId()));

        return customerFromDb;
    }

    public static String getCustomerColumnValue(String columnName, int customerId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT " + columnName + " FROM customer WHERE " + COLUMN_CUSTOMER_ID + " = " + customerId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }


    public static int insert(Customer newCustomer) throws SQLException {
        PreparedStatement insertCustomer;
        String insertString = "INSERT INTO customer (" +
                COLUMN_CUSTOMER_TYPE_ID + ", " +
                COLUMN_CUSTOMER_LAST_NAME+ ", " +
                COLUMN_CUSTOMER_FIRST_NAME + ", " +
                COLUMN_CUSTOMER_ADDRESS + ", " +
                COLUMN_CUSTOMER_EMAIL + ", " +
                COLUMN_CUSTOMER_PHONE + ", " +
                COLUMN_CUSTOMER_CURRENT_EXPENSES + ", " +
                COLUMN_CUSTOMER_PAY_SLIP + ", " +
                COLUMN_CUSTOMER_TAX_DETAILS + ", " +
                COLUMN_CUSTOMER_IDENTIFICATION_NUMBER + ", " +
                COLUMN_CUSTOMER_RATING + ", " +
                COLUMN_CUSTOMER_PASSWORD + ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        insertCustomer = databaseConnection.prepareStatement(insertString);
        insertCustomer.setInt(1,newCustomer.getCustomerTypeId());
        insertCustomer.setString(2, newCustomer.getLastName());
        insertCustomer.setString(3, newCustomer.getFirstName());
        insertCustomer.setString(4, newCustomer.getAddress());
        insertCustomer.setString(5, newCustomer.getEmail());
        insertCustomer.setString(6, newCustomer.getPhone());
        insertCustomer.setInt(7, newCustomer.getCurrentExpenses());
        insertCustomer.setInt(8, newCustomer.getPaySlip());
        insertCustomer.setInt(9, newCustomer.getTaxDetails());
        insertCustomer.setString(10, newCustomer.getIdentificationNumber());
        insertCustomer.setDouble(11, newCustomer.getRating());
        insertCustomer.setString(12, newCustomer.getPassword());

        insertCustomer.executeUpdate();


        newCustomer.setId(getCustomerId(newCustomer.getEmail()));
        return newCustomer.getId();
    }

    public static void update(String columnName, String newValue, int customerId) throws SQLException{
        PreparedStatement updateCustomer;
        String updateString = "UPDATE customer SET " +
                columnName + " = '" +
                newValue + "' WHERE " + COLUMN_CUSTOMER_ID + " = ?";
        updateCustomer = databaseConnection.prepareStatement(updateString);
        updateCustomer.setInt(1, customerId);

        updateCustomer.executeUpdate();
    }

    public static boolean checkEmail(String email){
        try{
            PreparedStatement checkIfMailExists;
            String checkMailString = "SELECT " + COLUMN_CUSTOMER_EMAIL + " FROM customer WHERE " + COLUMN_CUSTOMER_EMAIL + " = ?";
            checkIfMailExists = databaseConnection.prepareStatement(checkMailString);
            checkIfMailExists.setString(1, email);

            ResultSet resultSet = checkIfMailExists.executeQuery();

            return resultSet.next();
        }
        catch(Exception e){
            return false;
        }
    }
}
