package org.lendingtree.project;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class LoanDatabase {

    private static final String TABLE_LOAN = "loan";
    private static final String TABLE_CUSTOMER = "customer";
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
                + " WHERE " + COLUMN_CUSTOMER_ID + " = " + "?";

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

    public static void generateReport(int userId, boolean isCustomer, ArrayList<Integer> periods) throws Exception {
        try {
            int dayTo = 1;
            int institutionId = 1;
            String query = "";

            if (isCustomer) {
                query = "SELECT * " +
                        "FROM loan " +
                        "JOIN product " +
                        "ON product.product_id=loan.product_id " +
                        "JOIN loan_status " +
                        "ON loan_status.loan_status_id=loan.loan_status_id " +
                        "WHERE customer_id = ? ";
            } else {
                institutionId = getInstitutionId(userId);
                query = "SELECT * " +
                        "FROM institution " +
                        "JOIN representative " +
                        "ON institution.institution_id=representative.institution_id " +
                        "JOIN product " +
                        "ON product.representative_id=representative.representative_id " +
                        "JOIN loan " +
                        "ON loan.product_id=product.product_id " +
                        "JOIN loan_status " +
                        "ON loan_status.loan_status_id=loan.loan_status_id " +
                        "JOIN customer " +
                        "ON customer.customer_id=loan.customer_id " +
                        "WHERE representative.representative_id = ? ";
            }

            if (periods.size() > 0) {
                dayTo = getDayTo(periods.get(2), periods.get(3));
                query = query +
                        "AND loan_date_applied >= ? " +
                        "AND loan_date_applied <= ?";
            }

            PreparedStatement preparedStatement;

            preparedStatement = databaseConnection.prepareStatement(query);

            if (isCustomer) {
                preparedStatement.setInt(1, userId);
            } else {
                preparedStatement.setInt(1, institutionId);
            }

            if (periods.size() > 0) {
                preparedStatement.setString(2, periods.get(1) + "-" + periods.get(0) + "-01");
                preparedStatement.setString(3, periods.get(3) + "-" + periods.get(2)+ "-" + dayTo);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            FastReportBuilder fastReportBuilder = new FastReportBuilder();

            DynamicReport dynamicReport;

            if (isCustomer) {
                dynamicReport = fastReportBuilder
                        .addColumn("Loan Id", "loan_id", String.class.getName(), 15)
                        .addColumn("Product name", "product_description", String.class.getName(), 30)
                        .addColumn("Loan status", "loan_status_description", String.class.getName(), 15)
                        .addColumn("Loan amount", "product_amount", String.class.getName(), 50)
                        .addColumn("Date applied", "loan_date_applied", String.class.getName(), 20)
                        .setTitle("Customer's report")
                        .setSubtitle("This report was generated at " + new Date() + ".")
                        .setPrintBackgroundOnOddRows(true)
                        .setUseFullPageWidth(true)
                        .build();
            } else {
                dynamicReport = fastReportBuilder
                        .addColumn("Loan Id", "loan_id", String.class.getName(), 10)
                        .addColumn("Rep. Id", "representative_id", String.class.getName(), 10)
                        .addColumn("Product name", "product_description", String.class.getName(), 20)
                        .addColumn("Customer's first name", "customer_first_name", String.class.getName(), 20)
                        .addColumn("Customer's last name", "customer_last_name", String.class.getName(), 20)
                        .addColumn("Loan status", "loan_status_description", String.class.getName(), 15)
                        .addColumn("Loan amount", "product_amount", String.class.getName(), 50)
                        .addColumn("Date applied", "loan_date_applied", String.class.getName(), 20)
                        .setTitle("Institution's report")
                        .setSubtitle("This report was generated at " + new Date() + ".")
                        .setPrintBackgroundOnOddRows(true)
                        .setUseFullPageWidth(true)
                        .build();
            }

            JRResultSetDataSource jrResultSetDataSource = new JRResultSetDataSource(resultSet);

            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), jrResultSetDataSource);

            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getDayTo(int month, int year) {
        int dayTo = 1;

        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                dayTo = 31;
                break;

            case 2:
                if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
                    dayTo = 29;
                } else {
                    dayTo = 28;
                }
                break;

            case 4: case 6: case 9: case 11:
                dayTo = 30;
        }

        return dayTo;
    }

    public static int getInstitutionId(int representativeId) throws SQLException {
        int institutionId = 1;

        PreparedStatement preparedStatementRepresentative;

        String queryRepresentative = "SELECT * " +
                "FROM representative " +
                "JOIN institution " +
                "ON institution.institution_id=representative.institution_id " +
                "WHERE representative_id = ?";

        preparedStatementRepresentative = databaseConnection.prepareStatement(queryRepresentative);

        preparedStatementRepresentative.setInt(1, representativeId);

        ResultSet resultSetRepresentative = preparedStatementRepresentative.executeQuery();

        resultSetRepresentative.next();

        institutionId = resultSetRepresentative.getInt("institution_id");

        return institutionId;
    }

    public static void printLoansStatus() throws SQLException {

        PreparedStatement preparedStatementLoanStatus;

        String queryLoanStatus = "SELECT * FROM loan_status";

        preparedStatementLoanStatus = databaseConnection.prepareStatement(queryLoanStatus);

        ResultSet resultSetLoanStatus = preparedStatementLoanStatus.executeQuery();


        while (resultSetLoanStatus.next()) {
            System.out.println("Loan Status ID: " + "\t\t\t" + resultSetLoanStatus.getString(1));
            System.out.println("Loan Statutus Description: " + "\t\t" + resultSetLoanStatus.getString(2));
        }
    }

    public static void updateLoan(int loanId, int loanStatusId) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "UPDATE " + TABLE_LOAN +
                " SET " + COLUMN_LOAN_STATUS_ID + " = ?" +
                " WHERE " + COLUMN_LOAN_ID + " = ?;";

        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setInt(2, loanId);
        preparedStatement.setInt(1, loanStatusId);

        preparedStatement.executeUpdate();
    }

}
