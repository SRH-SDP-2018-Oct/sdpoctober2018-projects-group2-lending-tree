package org.lendingtree.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanDatabase {

    private static final String TABLE_LOAN = "loan";
    private static final String TABLE_CUSTOMER = "customer"; //extract
    private static final String COLUMN_LOAN_ID = "loan_id";
    private static final String COLUMN_CUSTOMER_ID = "customer_id";
    private static final String COLUMN_PRODUCT_ID = "product_id";
    private static final String COLUMN_LOAN_STATUS_ID = "loan_status_id";
    private static final String COLUMN_LOAN_DATE_APPLIED = "loan_date_applied";

    private static Connection databaseConnection = ConnectMSSQLServer.getConnection();

    public static void insertLoan (Loan newLoan) throws SQLException {
        PreparedStatement insertLoan;
        String insertString = "INSERT INTO loan (" +
                COLUMN_CUSTOMER_ID + ", " +
                COLUMN_PRODUCT_ID + ", " +
                COLUMN_LOAN_STATUS_ID + ", " +
                COLUMN_LOAN_DATE_APPLIED + ") " +
                "VALUES (?, ?, ?, ?)";

        insertLoan = databaseConnection.prepareStatement(insertString);

        insertLoan.setInt(1, newLoan.getCustomerId());
        insertLoan.setInt(2, newLoan.getProductId());
        insertLoan.setInt(3, newLoan.getLoanStatusId());
        insertLoan.setString(4, newLoan.getLoanDateApplied());

        insertLoan.executeUpdate();
    }
    
    public static void printCustomerLoans(int customerId) throws SQLException {

        PreparedStatement preparedStatementCustomer;

        String queryCustomer = "SELECT customer_first_name, customer_last_name FROM " + TABLE_CUSTOMER
                + " WHERE "+COLUMN_CUSTOMER_ID + " = " + "?";

       preparedStatementCustomer = databaseConnection.prepareStatement(queryCustomer);

        preparedStatementCustomer.setInt(1, customerId);

        PreparedStatement preparedStatementLoan;

        String queryLoan = "SELECT * FROM " + TABLE_LOAN + " WHERE " +
                COLUMN_CUSTOMER_ID + " = " + "?;";
        Boolean availableData = false;
        int loopCount = 0;

        preparedStatementLoan = databaseConnection.prepareStatement(queryLoan);

        preparedStatementLoan.setInt(1, customerId);


        ResultSet resultSetLoan = preparedStatementLoan.executeQuery();
        ResultSet resultSetCustomer = preparedStatementCustomer.executeQuery();

        resultSetCustomer.next();
        System.out.println("Loans from Customer: " +resultSetCustomer.getString(1)+ " " +resultSetCustomer.getString(2));

        while (resultSetLoan.next()) {
            loopCount++;
            System.out.println("----------" + loopCount + "----------");
            System.out.println("Loan ID: " + "\t\t\t" + resultSetLoan.getString(1));
            System.out.println("Customer ID: " + "\t\t" + resultSetLoan.getString(2));
            System.out.println("Product ID: " + "\t\t\t" + resultSetLoan.getString(3));
            System.out.println("Loan Status ID: " + "\t\t" + resultSetLoan.getString(4));
            System.out.println("Loan Date Applied: " + "\t\t\t\t" + resultSetLoan.getString(5));
            System.out.println("----------" + loopCount + "----------");
            availableData = true;
        }

        if (availableData) {
            System.out.println("\nNumber of records: " + loopCount + ".");
        } else {
            System.out.println("No data available.");
        }
    }

    public static void printRepresentativeLoans(int representativeId) throws SQLException {

        PreparedStatement preparedStatementRepresentative;

        String queryRepresentative = "SELECT representative_first_name, representative_last_name " +
                                     "FROM representative " +
                                     "WHERE representative_id = " + "?";

        preparedStatementRepresentative = databaseConnection.prepareStatement(queryRepresentative);

        preparedStatementRepresentative.setInt(1, representativeId);

        PreparedStatement preparedStatementLoan;
        String queryLoan = "SELECT * FROM loan " +
                "JOIN product " +
                "ON product.product_id=loan.product_id " +
                "JOIN representative " +
                "ON representative.representative_id=product.representative_id " +
                "WHERE representative.representative_id=?;";
        Boolean availableData = false;
        int loopCount = 0;

        preparedStatementLoan = databaseConnection.prepareStatement(queryLoan);

        preparedStatementLoan.setInt(1, representativeId);


        ResultSet resultSetLoan = preparedStatementLoan.executeQuery();
        ResultSet resultSetRepresentative = preparedStatementRepresentative.executeQuery();

        resultSetRepresentative.next();
        System.out.println("Loans from Representative: " +resultSetRepresentative.getString(1)+ " " +resultSetRepresentative.getString(2));

        while (resultSetLoan.next()) {
            loopCount++;
            System.out.println("----------" + loopCount + "----------");
            System.out.println("Loan ID: " + "\t\t\t" + resultSetLoan.getString(1));
            System.out.println("Customer ID: " + "\t\t" + resultSetLoan.getString(2));
            System.out.println("Product ID: " + "\t\t\t" + resultSetLoan.getString(3));
            System.out.println("Loan Status ID: " + "\t\t" + resultSetLoan.getString(4));
            System.out.println("Loan Date Applied: " + "\t\t\t\t" + resultSetLoan.getString(5));
            System.out.println("----------" + loopCount + "----------");
            availableData = true;
        }

        if (availableData) {
            System.out.println("\nNumber of records: " + loopCount + ".");
        } else {
            System.out.println("No data available.");
        }
    }

    public static void printInstitutionLoans(int institutionId) throws SQLException {

        PreparedStatement preparedStatementInstitution;

        String queryInstitution = "SELECT institution_name " +
                "FROM institution " +
                "WHERE institution_id = " + "?";

        preparedStatementInstitution = databaseConnection.prepareStatement(queryInstitution);

        preparedStatementInstitution.setInt(1, institutionId);

        PreparedStatement preparedStatementLoan;
        String queryLoan = "SELECT * FROM loan " +
                "JOIN product " +
                "ON product.product_id=loan.product_id " +
                "JOIN representative " +
                "ON representative.representative_id=product.representative_id " +
                "JOIN institution " +
                "ON institution.institution_id=representative.institution_id " +
                "WHERE institution.institution_id=?;";
        Boolean availableData = false;
        int loopCount = 0;

        preparedStatementLoan = databaseConnection.prepareStatement(queryLoan);

        preparedStatementLoan.setInt(1, institutionId);


        ResultSet resultSetLoan = preparedStatementLoan.executeQuery();
        ResultSet resultSetInstitution = preparedStatementInstitution.executeQuery();

        resultSetInstitution.next();
        System.out.println("Loans from Institution: " +resultSetInstitution.getString(1));

        while (resultSetLoan.next()) {
            loopCount++;
            System.out.println("----------" + loopCount + "----------");
            System.out.println("Loan ID: " + "\t\t\t" + resultSetLoan.getString(1));
            System.out.println("Customer ID: " + "\t\t" + resultSetLoan.getString(2));
            System.out.println("Product ID: " + "\t\t\t" + resultSetLoan.getString(3));
            System.out.println("Loan Status ID: " + "\t\t" + resultSetLoan.getString(4));
            System.out.println("Loan Date Applied: " + "\t\t\t\t" + resultSetLoan.getString(5));
            System.out.println("----------" + loopCount + "----------");
            availableData = true;
        }

        if (availableData) {
            System.out.println("\nNumber of records: " + loopCount + ".");
        } else {
            System.out.println("No data available.");
        }

    }
}
