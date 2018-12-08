package org.lendingtree.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanDatabase {

    private static final String TABLE_LOAN = "loan";
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
    
    public static void printLoans(int customerId) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "SELECT * FROM " + TABLE_LOAN + " WHERE " +
                COLUMN_CUSTOMER_ID + " = " + "?";
        Boolean availableData = false;
        int loopCount = 0;

        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setInt(1, customerId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            loopCount++;
            System.out.println("----------" + loopCount + "----------");
            System.out.println("Loan ID: " + "\t\t\t" + resultSet.getString(1));
            System.out.println("Customer ID: " + "\t\t" + resultSet.getString(2));
            System.out.println("Product ID: " + "\t\t\t" + resultSet.getString(3));
            System.out.println("Loan Status ID: " + "\t\t" + resultSet.getString(4));
            System.out.println("Loan Date Applied: " + "\t\t\t\t" + resultSet.getString(5));
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
