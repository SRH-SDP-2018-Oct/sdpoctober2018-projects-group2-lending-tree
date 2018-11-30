package org.lendingtree.project;

import java.sql.*;

public class ConnectMSSQLServer {
    private static String databaseURL = "jdbc:sqlserver://localhost:1433;DatabaseName=master;allowMultiQueries=true";
    private static String databaseUserName = "sa";
    private static String databaseUserPassword = "admin123";
    private static Connection databaseConnection;

    static {
        databaseConnection = getConnection();
    }

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            databaseConnection = DriverManager.getConnection(databaseURL, databaseUserName,
                    databaseUserPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseConnection;
    }

    public static void executeSqlUpdate(String sentence) throws SQLException {
        Statement dbStatement = databaseConnection.createStatement();
        dbStatement.executeUpdate(sentence);
    }

    public static void listCustomerTypes() throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT * FROM customer_type");
        while (dbResultSet.next()) {
            System.out.println(dbResultSet.getString(1) + ". " + dbResultSet.getString(2));
        }
    }

    public static int getCustomerId(String customerEmail) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_id FROM customer WHERE customer_email = '" + customerEmail + "'");
        dbResultSet.next();
        return Integer.parseInt(dbResultSet.getString(1));
    }

    public static String getCustomerType(int searchCustomerTypeId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_type_description FROM customer_type WHERE customer_type_id = " + searchCustomerTypeId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }

    public static Customer getCustomer(String customerEmail) throws SQLException{
        Customer customerFromDb = new Customer();

        customerFromDb.id = getCustomerId(customerEmail);
        customerFromDb.customerTypeId = getCustomerTypeId(customerFromDb.id);
        customerFromDb.customerType = getCustomerType(customerFromDb.customerTypeId);
        customerFromDb.lastName = getCustomerLastName(customerFromDb.id);
        customerFromDb.firstName = getCustomerFirstName(customerFromDb.id);
        customerFromDb.address = getCustomerAddress(customerFromDb.id);
        customerFromDb.email = customerEmail;
        customerFromDb.phone = getCustomerPhone(customerFromDb.id);
        customerFromDb.currentExpenses = getCustomerCurrentExpenses(customerFromDb.id);
        customerFromDb.paySlip = getCustomerPaySlip(customerFromDb.id);
        customerFromDb.taxDetails = getCustomerTaxDetails(customerFromDb.id);
        customerFromDb.identificationNumber = getCustomerIdentificationNumber(customerFromDb.id);
        customerFromDb.rating = getCustomerRating(customerFromDb.id);
        customerFromDb.password = getCustomerPassword(customerFromDb.id);

        return customerFromDb;
    }

    public static int getCustomerTypeId(int customerId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_type_id FROM customer WHERE customer_id = " + customerId);
        dbResultSet.next();
        return Integer.parseInt(dbResultSet.getString(1));
    }

    public static String getCustomerLastName(int customerId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_last_name FROM customer WHERE customer_id = " + customerId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }

    public static String getCustomerFirstName(int customerId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_first_name FROM customer WHERE customer_id = " + customerId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }

    public static String getCustomerAddress(int customerId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_address FROM customer WHERE customer_id = " + customerId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }

    public static String getCustomerPhone(int customerId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_phone FROM customer WHERE customer_id = " + customerId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }

    public static int getCustomerCurrentExpenses(int customerId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_current_expenses FROM customer WHERE customer_id = " + customerId);
        dbResultSet.next();
        return Integer.parseInt(dbResultSet.getString(1));
    }

    public static int getCustomerPaySlip(int customerId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_pay_slip FROM customer WHERE customer_id = " + customerId);
        dbResultSet.next();
        return Integer.parseInt(dbResultSet.getString(1));
    }

    public static int getCustomerTaxDetails(int customerId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_tax_details FROM customer WHERE customer_id = " + customerId);
        dbResultSet.next();
        return Integer.parseInt(dbResultSet.getString(1));
    }

    public static String getCustomerIdentificationNumber(int customerId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_identification_number FROM customer WHERE customer_id = " + customerId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }

    public static float getCustomerRating(int customerId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_rating FROM customer WHERE customer_id = " + customerId);
        dbResultSet.next();
        return Float.parseFloat(dbResultSet.getString(1));
    }

    public static String getCustomerPassword(int customerId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_password FROM customer WHERE customer_id = " + customerId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }

    public static int insertCustomer(Customer newCustomer) throws SQLException {
        executeSqlUpdate("INSERT INTO customer (customer_type_id, customer_last_name, customer_first_name, customer_address, customer_email, customer_phone, customer_current_expenses, customer_pay_slip, customer_tax_details, customer_identification_number, customer_rating, customer_password) " +
                "VALUES (" + newCustomer.customerTypeId + ", '" + newCustomer.lastName + "', '" + newCustomer.firstName + "', '" + newCustomer.address + "', '" + newCustomer.email + "', '" + newCustomer.phone + "', " + newCustomer.currentExpenses + ", " + newCustomer.paySlip + ", " + newCustomer.taxDetails + ", '" + newCustomer.identificationNumber + "', " +newCustomer.rating + ", '" + newCustomer.password + "')");
        return getCustomerId(newCustomer.email);
    }
}