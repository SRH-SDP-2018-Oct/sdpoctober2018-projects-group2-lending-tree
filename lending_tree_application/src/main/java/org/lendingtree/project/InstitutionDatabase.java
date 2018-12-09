package org.lendingtree.project;

import java.sql.*;

public class InstitutionDatabase {
    private static final String COLUMN_INSTITUTION_ID = "institution_id";
    private static final String COLUMN_INSTITUTION_TYPE_ID = "institution_type_id";
    private static final String COLUMN_INSTITUTION_NAME = "institution_name";
    private static final String COLUMN_INSTITUTION_ADDRESS = "institution_address";
    private static final String COLUMN_INSTITUTION_EMAIL = "institution_email";
    private static final String COLUMN_INSTITUTION_FINANCIAL_STATUS = "institution_financial_status";
    private static final String COLUMN_INSTITUTION_RATING = "institution_rating";


    private static Connection databaseConnection = ConnectMSSQLServer.getConnection();

    public static int listInstitutionTypes() throws SQLException {
        Statement dbStatement = databaseConnection.createStatement();
        int maxValueInstitutionTypes = 0;
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT * FROM institution_type");
        while (dbResultSet.next()) {
            System.out.println(dbResultSet.getString(1) + ". " + dbResultSet.getString(2));
            maxValueInstitutionTypes++;
        }
        return  maxValueInstitutionTypes;
    }

    public static String getInstitutionType(int searchInstitutionTypeId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT institution_type_description FROM institution_type WHERE " + COLUMN_INSTITUTION_TYPE_ID + " = " + searchInstitutionTypeId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }

    public static int getInstitutionId(String institutionEmail) throws SQLException{
        PreparedStatement dbPreparedStatement;
        String getInstitutionIdString = "SELECT " + COLUMN_INSTITUTION_ID + " FROM institution WHERE " + COLUMN_INSTITUTION_EMAIL + " = ?";
        dbPreparedStatement = databaseConnection.prepareStatement(getInstitutionIdString);
        dbPreparedStatement.setString(1, institutionEmail);
        ResultSet dbResultSet = dbPreparedStatement.executeQuery();
        dbResultSet.next();
        return Integer.parseInt(dbResultSet.getString(1));
    }

    public static Institution getInstitution(int institutionId) throws SQLException{
        Institution institutionFromDb = new Institution();

        institutionFromDb.setId(institutionId);
        institutionFromDb.setInstitutionTypeId(Integer.parseInt(getInstitutionColumnValue(COLUMN_INSTITUTION_TYPE_ID, institutionId)));
        institutionFromDb.setInstitutionType(getInstitutionType(institutionFromDb.getInstitutionTypeId()));
        institutionFromDb.setName(getInstitutionColumnValue(COLUMN_INSTITUTION_NAME, institutionId));
        institutionFromDb.setAddress(getInstitutionColumnValue(COLUMN_INSTITUTION_ADDRESS, institutionId));
        institutionFromDb.setEmail(getInstitutionColumnValue(COLUMN_INSTITUTION_EMAIL, institutionId));
        institutionFromDb.setFinancialStatus(getInstitutionColumnValue(COLUMN_INSTITUTION_FINANCIAL_STATUS, institutionId));
        institutionFromDb.setRating(Double.parseDouble(getInstitutionColumnValue(COLUMN_INSTITUTION_RATING, institutionId)));

        return institutionFromDb;
    }

    public static String getInstitutionColumnValue(String columnName, int institutionId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT " + columnName + " FROM institution WHERE " + COLUMN_INSTITUTION_ID + " = " + institutionId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }


    public static int insert(Institution newInstitution) throws SQLException {
        PreparedStatement insertInstitution;
        String insertString = "INSERT INTO institution (" +
                COLUMN_INSTITUTION_TYPE_ID + ", " +
                COLUMN_INSTITUTION_NAME + ", " +
                COLUMN_INSTITUTION_ADDRESS + ", " +
                COLUMN_INSTITUTION_EMAIL + ", " +
                COLUMN_INSTITUTION_FINANCIAL_STATUS + ", " +
                COLUMN_INSTITUTION_RATING + ") " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        insertInstitution = databaseConnection.prepareStatement(insertString);
        insertInstitution.setInt(1, newInstitution.getInstitutionTypeId());
        insertInstitution.setString(2, newInstitution.getName());
        insertInstitution.setString(3, newInstitution.getAddress());
        insertInstitution.setString(4, newInstitution.getEmail());
        insertInstitution.setString(5, newInstitution.getFinancialStatus());
        insertInstitution.setDouble(6, newInstitution.getRating());

        insertInstitution.executeUpdate();


        newInstitution.setId(getInstitutionId(newInstitution.getEmail()));
        return newInstitution.getId();
    }

    public static void update(String columnName, String newValue, int institutionId) throws SQLException{
        PreparedStatement updateCustomer;
        String updateString = "UPDATE institution SET " +
                columnName + " = '" +
                newValue + "' WHERE " + COLUMN_INSTITUTION_ID + " = ?";
        updateCustomer = databaseConnection.prepareStatement(updateString);
        updateCustomer.setInt(1, institutionId);

        updateCustomer.executeUpdate();
    }

    public static boolean checkEmail(String email){
        try{
            PreparedStatement checkIfMailExists;
            String checkMailString = "SELECT " + COLUMN_INSTITUTION_EMAIL + " FROM representative WHERE " + COLUMN_INSTITUTION_EMAIL + " = ?";
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
