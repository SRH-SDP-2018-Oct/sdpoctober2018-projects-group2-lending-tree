package org.lendingtree.project;

import java.sql.*;

public class RepresentativeDatabase {
    private static final String COLUMN_REPRESENTATIVE_ID = "representative_id";
    private static final String COLUMN_INSTITUTION_ID = "institution_id";
    private static final String COLUMN_REPRESENTATIVE_LAST_NAME = "representative_last_name";
    private static final String COLUMN_REPRESENTATIVE_FIRST_NAME = "representative_first_name";
    private static final String COLUMN_REPRESENTATIVE_EMAIL = "representative_email";
    private static final String COLUMN_REPRESENTATIVE_PHONE = "representative_phone";
    private static final String COLUMN_INSTITUTION_DEPARTMENT_ID = "institution_department_id";
    private static final String COLUMN_REPRESENTATIVE_PASSWORD = "representative_password";


    private static Connection databaseConnection = ConnectMSSQLServer.getConnection();

    public static int listInstitutionDepartments() throws SQLException {
        Statement dbStatement = databaseConnection.createStatement();
        int maxValueInstitutionDepartment = 0;
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT * FROM institution_department");
        while (dbResultSet.next()) {
            System.out.println(dbResultSet.getString(1) + ". " + dbResultSet.getString(2));
            maxValueInstitutionDepartment++;
        }
        return  maxValueInstitutionDepartment;
    }

    public static int listInstitutions() throws SQLException {
        Statement dbStatement = databaseConnection.createStatement();
        int maxValueInstitutions = 0;
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT * FROM institution");
        while (dbResultSet.next()) {
            System.out.println(dbResultSet.getString(1) + ". " + dbResultSet.getString(2));
            maxValueInstitutions++;
        }
        return  maxValueInstitutions;
    }

    public static int getRepresentativeId(String representativeEmail) throws SQLException{
        PreparedStatement dbPreparedStatement;
        String getRepresentativeIdString = "SELECT " + COLUMN_REPRESENTATIVE_ID + " FROM representative WHERE " + COLUMN_REPRESENTATIVE_EMAIL + " = ?";
        dbPreparedStatement = databaseConnection.prepareStatement(getRepresentativeIdString);
        dbPreparedStatement.setString(1, representativeEmail);
        ResultSet dbResultSet = dbPreparedStatement.executeQuery();
        dbResultSet.next();
        return Integer.parseInt(dbResultSet.getString(1));
    }

    public static String getInstitutionDepartment(int searchInstitutionDepartmentId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT institution_department_description FROM institution_department WHERE " + COLUMN_INSTITUTION_DEPARTMENT_ID + " = " + searchInstitutionDepartmentId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }

    public static Representative getRepresentative(String representativeEmail) throws SQLException{
        Representative representativeFromDb = new Representative();

        representativeFromDb.setId(getRepresentativeId(representativeEmail));
        representativeFromDb.setInstitution(InstitutionDatabase.getInstitution(Integer.parseInt(getRepresentativeColumnValue(COLUMN_INSTITUTION_ID, representativeFromDb.getId()))));
        representativeFromDb.setLastName(getRepresentativeColumnValue(COLUMN_REPRESENTATIVE_LAST_NAME, representativeFromDb.getId()));
        representativeFromDb.setFirstName(getRepresentativeColumnValue(COLUMN_REPRESENTATIVE_FIRST_NAME, representativeFromDb.getId()));
        representativeFromDb.setEmail(representativeEmail);
        representativeFromDb.setPhone(getRepresentativeColumnValue(COLUMN_REPRESENTATIVE_PHONE, representativeFromDb.getId()));
        representativeFromDb.setPassword(getRepresentativeColumnValue(COLUMN_REPRESENTATIVE_PASSWORD, representativeFromDb.getId()));
        representativeFromDb.setInstitutionDepartmentId(Integer.parseInt(getRepresentativeColumnValue(COLUMN_INSTITUTION_DEPARTMENT_ID, representativeFromDb.getId())));
        representativeFromDb.setInstitutionDepartment(getInstitutionDepartment(representativeFromDb.getInstitutionDepartmentId()));

        return representativeFromDb;
    }

    public static String getRepresentativeColumnValue(String columnName, int representativeId) throws SQLException{
        Statement dbStatement = databaseConnection.createStatement();
        ResultSet dbResultSet = dbStatement.executeQuery("SELECT " + columnName + " FROM representative WHERE " + COLUMN_REPRESENTATIVE_ID + " = " + representativeId);
        dbResultSet.next();
        return dbResultSet.getString(1);
    }


    public static int insert(Representative newRepresentative) throws SQLException {
        PreparedStatement insertRepresentative;
        String insertString = "INSERT INTO representative (" +
                COLUMN_INSTITUTION_ID + ", " +
                COLUMN_REPRESENTATIVE_LAST_NAME + ", " +
                COLUMN_REPRESENTATIVE_FIRST_NAME + ", " +
                COLUMN_REPRESENTATIVE_EMAIL + ", " +
                COLUMN_REPRESENTATIVE_PHONE + ", " +
                COLUMN_INSTITUTION_DEPARTMENT_ID + ", " +
                COLUMN_REPRESENTATIVE_PASSWORD + ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        insertRepresentative = databaseConnection.prepareStatement(insertString);
        insertRepresentative.setInt(1, newRepresentative.getInstitution().getId());
        insertRepresentative.setString(2, newRepresentative.getLastName());
        insertRepresentative.setString(3, newRepresentative.getFirstName());
        insertRepresentative.setString(4, newRepresentative.getEmail());
        insertRepresentative.setString(5, newRepresentative.getPhone());
        insertRepresentative.setInt(6, newRepresentative.getInstitutionDepartmentId());
        insertRepresentative.setString(7, newRepresentative.getPassword());

        insertRepresentative.executeUpdate();


        newRepresentative.setId(getRepresentativeId(newRepresentative.getEmail()));
        return newRepresentative.getId();
    }

    public static void update(String columnName, String newValue, int representativeId) throws SQLException{
        PreparedStatement updateCustomer;
        String updateString = "UPDATE representative SET " +
                columnName + " = '" +
                newValue + "' WHERE " + COLUMN_REPRESENTATIVE_ID + " = ?";
        updateCustomer = databaseConnection.prepareStatement(updateString);
        updateCustomer.setInt(1, representativeId);

        updateCustomer.executeUpdate();
    }

    public static boolean checkEmail(String email){
        try{
            PreparedStatement checkIfMailExists;
            String checkMailString = "SELECT " + COLUMN_REPRESENTATIVE_EMAIL + " FROM representative WHERE " + COLUMN_REPRESENTATIVE_EMAIL + " = ?";
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
