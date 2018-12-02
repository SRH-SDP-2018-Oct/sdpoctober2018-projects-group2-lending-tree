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
            databaseConnection = DriverManager.getConnection(databaseURL, databaseUserName, databaseUserPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseConnection;
    }

    private static void executeSqlUpdate(String sentence) throws SQLException {
        //System.out.println(sentence);
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
        customerFromDb.customerTypeId = Integer.parseInt(getCustomerColumnValue(customerFromDb.dbColumnCustomerTypeId, customerFromDb.id));
        customerFromDb.customerType = getCustomerType(customerFromDb.customerTypeId);
        customerFromDb.lastName = getCustomerColumnValue(customerFromDb.dbColumnCustomerLastName, customerFromDb.id);
        customerFromDb.firstName = getCustomerColumnValue(customerFromDb.dbColumnCustomerFirstName, customerFromDb.id);
        customerFromDb.address = getCustomerColumnValue(customerFromDb.dbColumnCustomerAddress, customerFromDb.id);
        customerFromDb.email = customerEmail;
        customerFromDb.phone = getCustomerColumnValue(customerFromDb.dbColumnCustomerPhone, customerFromDb.id);
        customerFromDb.currentExpenses = Integer.parseInt(getCustomerColumnValue(customerFromDb.dbColumnCustomerCurrentExpenses, customerFromDb.id));
        customerFromDb.paySlip = Integer.parseInt(getCustomerColumnValue(customerFromDb.dbColumnCustomerPaySlip, customerFromDb.id));
        customerFromDb.taxDetails = Integer.parseInt(getCustomerColumnValue(customerFromDb.dbColumnCustomerTaxDetails, customerFromDb.id));
        customerFromDb.identificationNumber = getCustomerColumnValue(customerFromDb.dbColumnCustomerIdentificationNumber, customerFromDb.id);
        customerFromDb.rating = Float.parseFloat(getCustomerColumnValue(customerFromDb.dbColumnCustomerRating, customerFromDb.id));
        customerFromDb.password = getCustomerColumnValue(customerFromDb.dbColumnCustomerPassword, customerFromDb.id);

        return customerFromDb;
    }

    public static String getCustomerColumnValue(String columnName, int customerId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT " + columnName + " FROM customer WHERE customer_id = " + customerId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }


    public static int insertCustomer(Customer newCustomer) throws SQLException {
        executeSqlUpdate("INSERT INTO customer (" +
                newCustomer.dbColumnCustomerTypeId + ", " +
                newCustomer.dbColumnCustomerLastName+ ", " +
                newCustomer.dbColumnCustomerFirstName + ", " +
                newCustomer.dbColumnCustomerAddress + ", " +
                newCustomer.dbColumnCustomerEmail + ", " +
                newCustomer.dbColumnCustomerPhone + ", " +
                newCustomer.dbColumnCustomerCurrentExpenses + ", " +
                newCustomer.dbColumnCustomerPaySlip + ", " +
                newCustomer.dbColumnCustomerTaxDetails + ", " +
                newCustomer.dbColumnCustomerIdentificationNumber + ", " +
                newCustomer.dbColumnCustomerRating + ", " +
                newCustomer.dbColumnCustomerPassword + ") " +
                "VALUES (" +
                newCustomer.customerTypeId + ", '" +
                newCustomer.lastName + "', '" +
                newCustomer.firstName + "', '" +
                newCustomer.address + "', '" +
                newCustomer.email + "', '" +
                newCustomer.phone + "', " +
                newCustomer.currentExpenses + ", " +
                newCustomer.paySlip + ", " +
                newCustomer.taxDetails + ", '" +
                newCustomer.identificationNumber + "', " +
                newCustomer.rating + ", '" +
                newCustomer.password + "')");
        return getCustomerId(newCustomer.email);
    }

    public static void updateCostumerValue(String columnName, String newValue, int customerId) throws SQLException{
        executeSqlUpdate("UPDATE customer SET " +
                columnName + " = '" +
                newValue + "' WHERE customer_id = " +
                customerId);
    }
}