package org.lendingtree.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDatabase {

    private static final String COLUMN_PRODUCT_ID = "product_id";
    private static final String COLUMN_PRODUCT_TYPE_ID = "product_type_id";
    private static final String COLUMN_PRODUCT_DESCRIPTION = "product_description";
    private static final String COLUMN_PRODUCT_REPRESENTATIVE_ID = "representative_id";
    private static final String COLUMN_PRODUCT_AMOUNT = "product_amount";
    private static final String COLUMN_PRODUCT_INTEREST_RATE = "product_interest_rate";
    private static final String COLUMN_PRODUCT_NUMBER_OF_PAYMENTS = "product_number_of_payments";
    private static final String COLUMN_PRODUCT_AVAILABILITY_FROM = "product_availability_from";
    private static final String COLUMN_PRODUCT_AVAILABILITY_TO = "product_availability_to";
    private static final String COLUMN_PRODUCT_ACTIVE_STATUS = "product_active_status";

    private static Connection databaseConnection = ConnectMSSQLServer.getConnection();

    public static void insertProduct(Product newProduct) throws SQLException {
        PreparedStatement insertProduct;
        String insertString = "INSERT INTO product (" +
                COLUMN_PRODUCT_TYPE_ID + ", " +
                COLUMN_PRODUCT_DESCRIPTION+ ", " +
                COLUMN_PRODUCT_REPRESENTATIVE_ID + ", " +
                COLUMN_PRODUCT_AMOUNT + ", " +
                COLUMN_PRODUCT_INTEREST_RATE + ", " +
                COLUMN_PRODUCT_NUMBER_OF_PAYMENTS + ", " +
                COLUMN_PRODUCT_AVAILABILITY_FROM + ", " +
                COLUMN_PRODUCT_AVAILABILITY_TO + ", " +
                COLUMN_PRODUCT_ACTIVE_STATUS + ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        insertProduct = databaseConnection.prepareStatement(insertString);

        insertProduct.setInt(1, newProduct.getProductTypeId());
        insertProduct.setString(2, newProduct.getProductDescription());
        insertProduct.setInt(3, newProduct.getRepresentativeId());
        insertProduct.setDouble(4, newProduct.getProductAmount());
        insertProduct.setDouble(5, newProduct.getProductInterestRate());
        insertProduct.setInt(6, newProduct.getProductNumberOfPayments());
        insertProduct.setString(    7, newProduct.getProductAvailabilityFrom());
        insertProduct.setString(8, newProduct.getProductAvailabilityTo());
        insertProduct.setBoolean(9, newProduct.getProductActiveStatus());

        insertProduct.executeUpdate();
    }

    public static void printProducts(int representativeId) throws SQLException {
        PreparedStatement printProducts;
        String printString = "SELECT * FROM product WHERE " +
                COLUMN_PRODUCT_REPRESENTATIVE_ID + " = " + "?";
        Boolean availableData = false;
        int loopCount = 0;

        printProducts = databaseConnection.prepareStatement(printString);

        printProducts.setInt(1, representativeId);

        ResultSet resultSet = printProducts.executeQuery();

        while (resultSet.next()) {
            loopCount++;
            System.out.println("----------" + loopCount + "----------");
            System.out.println("Product ID: " + "\t\t\t" + resultSet.getString(1));
            System.out.println("Product type ID: " + "\t\t" + resultSet.getString(2)); //EXTRACT PRODUCT TYPE NAME
            System.out.println("Description: " + "\t\t\t" + resultSet.getString(3));
            System.out.println("Representative ID: " + "\t\t" + resultSet.getString(4)); //EXTRACT REPRESENTATIVE NAME
            System.out.println("Amount: " + "\t\t\t\t" + resultSet.getString(5));
            System.out.println("Interest rate: " + "\t\t\t" + resultSet.getString(6));
            System.out.println("Number of payments: " + "\t" + resultSet.getString(7));
            System.out.println("Available from: " + "\t\t" + resultSet.getString(8));
            System.out.println("Available to: " + "\t\t\t" + resultSet.getString(9));
            System.out.println("Status: " + "\t\t\t\t" + resultSet.getString(10)); //TRANSFORM TO YES OR NO
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