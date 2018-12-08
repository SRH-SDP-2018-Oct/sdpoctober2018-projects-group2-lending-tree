package org.lendingtree.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanDatabase {

    private static final String COLUMN_LOAN_ID = "loan_id";
    private static final String COLUMN_CUSTOMER_ID = "customer_id";
    private static final String COLUMN_PRODUCT_ID = "product_id";
    private static final String COLUMN_LOAN_STATUS_ID = "loan_status_id";
    private static final String COLUMN_LOAN_DATE_APPLIED = "loan_date_applied";

    private static Connection databaseConnection = ConnectMSSQLServer.getConnection();

    public static void insertLoan (Loan newLoan) throws SQLException {
        PreparedStatement insertLoan;
        String insertString = "INSERT INTO loan (" +
                COLUMN_LOAN_ID + ", " +
                COLUMN_CUSTOMER_ID + ", " +
                COLUMN_PRODUCT_ID + ", " +
                COLUMN_LOAN_STATUS_ID + ", " +
                COLUMN_LOAN_DATE_APPLIED + ") " +
                "VALUES (?, ?, ?, ?, ?)";

        insertLoan = databaseConnection.prepareStatement(insertString);

        insertLoan.setInt(1, newLoan.getLoanId());
        insertLoan.setInt(2, newLoan.getCustomerId());
        insertLoan.setInt(3, newLoan.getProductId());
        insertLoan.setInt(4, newLoan.getLoanStatusId());
        insertLoan.setString(5, newLoan.getLoanDateApplied());

        insertLoan.executeUpdate();
    }

}
