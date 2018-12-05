package org.lendingtree.project;

import java.sql.*;

public class CustomerDatabase {
    private static final String dbColumnCustomerId = "customer_id";
    private static final String dbColumnCustomerTypeId = "customer_type_id";
    private static final String dbColumnCustomerLastName = "customer_last_name";
    private static final String dbColumnCustomerFirstName = "customer_first_name";
    private static final String dbColumnCustomerAddress = "customer_address";
    private static final String dbColumnCustomerEmail = "customer_email";
    private static final String dbColumnCustomerPhone = "customer_phone";
    private static final String dbColumnCustomerCurrentExpenses = "customer_current_expenses";
    private static final String dbColumnCustomerPaySlip = "customer_pay_slip";
    private static final String dbColumnCustomerTaxDetails = "customer_tax_details";
    private static final String dbColumnCustomerIdentificationNumber = "customer_identification_number";
    private static final String dbColumnCustomerRating = "customer_rating";
    private static final String dbColumnCustomerPassword = "customer_password";

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
        String getCustomerIdString = "SELECT " + dbColumnCustomerId + " FROM customer WHERE " + dbColumnCustomerEmail + " = ?";
        dbPreparedStatement = databaseConnection.prepareStatement(getCustomerIdString);
        dbPreparedStatement.setString(1, customerEmail);
        ResultSet dbResultSet = dbPreparedStatement.executeQuery();
        dbResultSet.next();
        return Integer.parseInt(dbResultSet.getString(1));
    }

    public static String getCustomerType(int searchCustomerTypeId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT customer_type_description FROM customer_type WHERE " + dbColumnCustomerTypeId + " = " + searchCustomerTypeId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }

    public static Customer getCustomer(String customerEmail) throws SQLException{
        Customer customerFromDb = new Customer();

        customerFromDb.setId(getCustomerId(customerEmail));
        customerFromDb.setCustomerTypeId(Integer.parseInt(getCustomerColumnValue(dbColumnCustomerTypeId, customerFromDb.getId())));
        customerFromDb.setCustomerType(getCustomerType(customerFromDb.getCustomerTypeId()));
        customerFromDb.setLastName(getCustomerColumnValue(dbColumnCustomerLastName, customerFromDb.getId()));
        customerFromDb.setFirstName(getCustomerColumnValue(dbColumnCustomerFirstName, customerFromDb.getId()));
        customerFromDb.setAddress(getCustomerColumnValue(dbColumnCustomerAddress, customerFromDb.getId()));
        customerFromDb.setEmail(customerEmail);
        customerFromDb.setPhone(getCustomerColumnValue(dbColumnCustomerPhone, customerFromDb.getId()));
        customerFromDb.setCurrentExpenses(Integer.parseInt(getCustomerColumnValue(dbColumnCustomerCurrentExpenses, customerFromDb.getId())));
        customerFromDb.setPaySlip(Integer.parseInt(getCustomerColumnValue(dbColumnCustomerPaySlip, customerFromDb.getId())));
        customerFromDb.setTaxDetails(Integer.parseInt(getCustomerColumnValue(dbColumnCustomerTaxDetails, customerFromDb.getId())));
        customerFromDb.setIdentificationNumber(getCustomerColumnValue(dbColumnCustomerIdentificationNumber, customerFromDb.getId()));
        customerFromDb.setRating(Float.parseFloat(getCustomerColumnValue(dbColumnCustomerRating, customerFromDb.getId())));
        customerFromDb.setPassword(getCustomerColumnValue(dbColumnCustomerPassword, customerFromDb.getId()));

        return customerFromDb;
    }

    public static String getCustomerColumnValue(String columnName, int customerId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT " + columnName + " FROM customer WHERE " + dbColumnCustomerId + " = " + customerId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }


    public static int insert(Customer newCustomer) throws SQLException {
        PreparedStatement insertCustomer;
        String insertString = "INSERT INTO customer (" +
                dbColumnCustomerTypeId + ", " +
                dbColumnCustomerLastName+ ", " +
                dbColumnCustomerFirstName + ", " +
                dbColumnCustomerAddress + ", " +
                dbColumnCustomerEmail + ", " +
                dbColumnCustomerPhone + ", " +
                dbColumnCustomerCurrentExpenses + ", " +
                dbColumnCustomerPaySlip + ", " +
                dbColumnCustomerTaxDetails + ", " +
                dbColumnCustomerIdentificationNumber + ", " +
                dbColumnCustomerRating + ", " +
                dbColumnCustomerPassword + ") " +
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
        insertCustomer.setFloat(11, newCustomer.getRating());
        insertCustomer.setString(12, newCustomer.getPassword());

        insertCustomer.executeUpdate();


        newCustomer.setId(getCustomerId(newCustomer.getEmail()));
        return newCustomer.getId();
    }

    public static void update(String columnName, String newValue, int customerId) throws SQLException{
        PreparedStatement updateCustomer;
        String updateString = "UPDATE customer SET " +
                columnName + " = '" +
                newValue + "' WHERE " + dbColumnCustomerId + " = ?";
        updateCustomer = databaseConnection.prepareStatement(updateString);
        updateCustomer.setInt(1, customerId);

        updateCustomer.executeUpdate();
    }

    public static boolean checkEmail(String email){
        try{
            PreparedStatement checkIfMailExists;
            String checkMailString = "SELECT " + dbColumnCustomerEmail + " FROM customer WHERE " + dbColumnCustomerEmail + " = ?";
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
