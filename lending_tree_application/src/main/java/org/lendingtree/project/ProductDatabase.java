package org.lendingtree.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDatabase {

    private static final String TABLE_PRODUCT = "product";
    private static final String TABLE_PRODUCT_TYPE = "product_type";
    private static final String TABLE_REPRESENTATIVE = "representative";
    private static final String TABLE_INSTITUTION = "institution";
    private static final String TABLE_INSTITUTION_DEPARTMENT = "institution_department";
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
    private static final String COLUMN_INSTITUTION_ID = "institution_id";
    private static final String COLUMN_INSTITUTION_NAME = "institution_name";
    private static final String COLUMN_INSTITUTION_RATING = "institution_rating";
    private static final String COLUMN_INSTITUTION_DEPARTMENT_ID = "institution_department_id";
    private static final String COLUMN_INSTITUTION_DEPARTMENT_DESCRIPTION = "institution_department_description";
    private static final String COLUMN_PRODUCT_TYPE_DESCRIPTION = "product_type_description";
    private static final String COLUMN_REPRESENTATIVE_FIRST_NAME = "representative_first_name";
    private static final String COLUMN_REPRESENTATIVE_LAST_NAME = "representative_last_name";
    private static final String COLUMN_REPRESENTATIVE_EMAIL = "representative_email";
    private static final String COLUMN_REPRESENTATIVE_PHONE = "representative_phone";

    private static Connection databaseConnection = ConnectMSSQLServer.getConnection();

    public static void createProduct(Product newProduct) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO " + TABLE_PRODUCT + " (" +
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

        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setInt(1, newProduct.getProductTypeId());
        preparedStatement.setString(2, newProduct.getProductDescription());
        preparedStatement.setInt(3, newProduct.getRepresentativeId());
        preparedStatement.setDouble(4, newProduct.getProductAmount());
        preparedStatement.setDouble(5, newProduct.getProductInterestRate());
        preparedStatement.setInt(6, newProduct.getProductNumberOfPayments());
        preparedStatement.setString(    7, newProduct.getProductAvailabilityFrom());
        preparedStatement.setString(8, newProduct.getProductAvailabilityTo());
        preparedStatement.setBoolean(9, newProduct.getProductActiveStatus());

        preparedStatement.executeUpdate();
    }

    public static void updateProduct(Product product) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "UPDATE " + TABLE_PRODUCT +
                " SET " + COLUMN_PRODUCT_DESCRIPTION + " = ?" +
                " WHERE " + COLUMN_PRODUCT_ID + " = ?";

        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setString(1, product.getProductDescription());
        preparedStatement.setInt(2, product.getProductId());

        preparedStatement.executeUpdate();
    }

    public static void disableProduct(Product product) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "UPDATE " + TABLE_PRODUCT +
                " SET " + COLUMN_PRODUCT_ACTIVE_STATUS + " = ?" +
                " WHERE " + COLUMN_PRODUCT_ID + " = ?";

        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setBoolean(1, product.getProductActiveStatus());
        preparedStatement.setInt(2, product.getProductId());

        preparedStatement.executeUpdate();
    }

    public static void getProductFromRepresentative(int representativeId) throws SQLException {
        //This method includes some hard-coded variables. It needs to be checked.
        PreparedStatement preparedStatement;
        String query = "SELECT * FROM " + TABLE_PRODUCT +
                " INNER JOIN " + TABLE_PRODUCT_TYPE +
                " ON " + TABLE_PRODUCT_TYPE + "." + COLUMN_PRODUCT_TYPE_ID + " = " +
                TABLE_PRODUCT + "." + COLUMN_PRODUCT_TYPE_ID +
                " INNER JOIN " + TABLE_REPRESENTATIVE +
                " ON " + TABLE_REPRESENTATIVE + "." + COLUMN_PRODUCT_REPRESENTATIVE_ID + " = " +
                TABLE_PRODUCT + "." + COLUMN_PRODUCT_REPRESENTATIVE_ID +
                " INNER JOIN " + TABLE_INSTITUTION_DEPARTMENT +
                " ON " + TABLE_INSTITUTION_DEPARTMENT + "." + COLUMN_INSTITUTION_DEPARTMENT_ID + " = " +
                TABLE_REPRESENTATIVE + "." + COLUMN_INSTITUTION_DEPARTMENT_ID +
                " INNER JOIN " + TABLE_INSTITUTION +
                " ON " + TABLE_INSTITUTION + "." + COLUMN_INSTITUTION_ID + " = " +
                TABLE_REPRESENTATIVE + "." + COLUMN_INSTITUTION_ID +
                " WHERE " + TABLE_PRODUCT + "." + COLUMN_PRODUCT_REPRESENTATIVE_ID + " = ?";
        Boolean availableData = false;
        int loopCount = 0;
        String activeStatus = "Not available";

        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setInt(1, representativeId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
                loopCount++;
                System.out.println("----------" + loopCount + "----------");
                System.out.println("Product ID: " + "\t\t\t" + resultSet.getInt(COLUMN_PRODUCT_ID));
                System.out.println("Description: " + "\t\t\t" + resultSet.getString(COLUMN_PRODUCT_DESCRIPTION));
                System.out.println("Product type ID: " + "\t\t" + resultSet.getInt(COLUMN_PRODUCT_TYPE_ID));
                System.out.println("Product type: " + "\t\t\t" + resultSet.getString(COLUMN_PRODUCT_TYPE_DESCRIPTION));
                System.out.println("Representative ID: " + "\t\t" + resultSet.getInt(COLUMN_PRODUCT_REPRESENTATIVE_ID));
                System.out.println("Representative: " + "\t\t" + resultSet.getString(COLUMN_REPRESENTATIVE_FIRST_NAME) +
                        " " + resultSet.getString(COLUMN_REPRESENTATIVE_LAST_NAME));
                System.out.println("Department ID: " + "\t\t\t" + resultSet.getInt(COLUMN_INSTITUTION_DEPARTMENT_ID));
                System.out.println("Department: " + "\t\t\t" + resultSet.getString(COLUMN_INSTITUTION_DEPARTMENT_DESCRIPTION));
                System.out.println("Institution ID: " + "\t\t" + resultSet.getInt(COLUMN_INSTITUTION_ID));
                System.out.println("Institution: " + "\t\t\t" + resultSet.getString(COLUMN_INSTITUTION_NAME));
                System.out.println("Amount: " + "\t\t\t\t" + resultSet.getDouble(COLUMN_PRODUCT_AMOUNT));
                System.out.println("Interest rate: " + "\t\t\t" + resultSet.getDouble(COLUMN_PRODUCT_INTEREST_RATE));
                System.out.println("Number of payments: " + "\t" + resultSet.getInt(COLUMN_PRODUCT_NUMBER_OF_PAYMENTS));
                System.out.println("Available from: " + "\t\t" + resultSet.getString(COLUMN_PRODUCT_AVAILABILITY_FROM));
                System.out.println("Available to: " + "\t\t\t" + resultSet.getString(COLUMN_PRODUCT_AVAILABILITY_TO));
                if (resultSet.getBoolean(COLUMN_PRODUCT_ACTIVE_STATUS)) {
                    activeStatus = "Active";
                } else {
                    activeStatus = "Inactive";
                }
                System.out.println("Status: " + "\t\t\t\t" + activeStatus);
                System.out.println("----------" + loopCount + "----------\n");
                availableData = true;
        }

        if (availableData) {
            System.out.println("\nNumber of records: " + loopCount + ".");
        } else {
            System.out.println("No data available.");
        }
    }

    public static void getAllActiveProduct(boolean activeStatus) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "SELECT * FROM " + TABLE_PRODUCT +
                " INNER JOIN " + TABLE_PRODUCT_TYPE +
                " ON " + TABLE_PRODUCT_TYPE + "." + COLUMN_PRODUCT_TYPE_ID + " = " +
                TABLE_PRODUCT + "." + COLUMN_PRODUCT_TYPE_ID +
                " INNER JOIN " + TABLE_REPRESENTATIVE +
                " ON " + TABLE_REPRESENTATIVE + "." + COLUMN_PRODUCT_REPRESENTATIVE_ID + " = " +
                TABLE_PRODUCT + "." + COLUMN_PRODUCT_REPRESENTATIVE_ID +
                " INNER JOIN " + TABLE_INSTITUTION +
                " ON " + TABLE_INSTITUTION + "." + COLUMN_INSTITUTION_ID + " = " +
                TABLE_REPRESENTATIVE + "." + COLUMN_INSTITUTION_ID +
                " WHERE " + COLUMN_PRODUCT_ACTIVE_STATUS + " = ?";
        Boolean availableData = false;
        int loopCount = 0;

        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setBoolean(1, activeStatus);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            loopCount++;
            System.out.println("----------" + loopCount + "----------");
            System.out.println("Product ID: " + "\t\t\t" + resultSet.getInt(COLUMN_PRODUCT_ID));
            System.out.println("Description: " + "\t\t\t" + resultSet.getString(COLUMN_PRODUCT_DESCRIPTION));
            //System.out.println("Product type ID: " + "\t\t" + resultSet.getInt(COLUMN_PRODUCT_TYPE_ID));
            System.out.println("Product type: " + "\t\t\t" + resultSet.getString(COLUMN_PRODUCT_TYPE_DESCRIPTION));
            //System.out.println("Representative ID: " + "\t\t" + resultSet.getInt(COLUMN_PRODUCT_REPRESENTATIVE_ID));
            System.out.println("Representative: " + "\t\t" + resultSet.getString(COLUMN_REPRESENTATIVE_FIRST_NAME) +
                    " " + resultSet.getString(COLUMN_REPRESENTATIVE_LAST_NAME));
            System.out.println("Institution: " + "\t\t\t" + resultSet.getString(COLUMN_INSTITUTION_NAME));
            System.out.println("Amount: " + "\t\t\t\t" + resultSet.getDouble(COLUMN_PRODUCT_AMOUNT));
            System.out.println("Interest rate: " + "\t\t\t" + resultSet.getDouble(COLUMN_PRODUCT_INTEREST_RATE));
            System.out.println("Number of payments: " + "\t" + resultSet.getInt(COLUMN_PRODUCT_NUMBER_OF_PAYMENTS));
            System.out.println("Available from: " + "\t\t" + resultSet.getString(COLUMN_PRODUCT_AVAILABILITY_FROM));
            System.out.println("Available to: " + "\t\t\t" + resultSet.getString(COLUMN_PRODUCT_AVAILABILITY_TO));
            System.out.println("Status: " + "\t\t\t\t" + "Active");
            System.out.println("----------" + loopCount + "----------\n");
            availableData = true;
        }

        if (availableData) {
            System.out.println("\nNumber of records: " + loopCount + ".");
        } else {
            System.out.println("No data available.");
        }
    }

    public static void getProductDetails(ArrayList<Integer> productIds, boolean activeStatus) throws SQLException {
        int arraySize = productIds.size();

        PreparedStatement preparedStatement;
        String query = "SELECT * FROM " + TABLE_PRODUCT +
                " INNER JOIN " + TABLE_PRODUCT_TYPE +
                " ON " + TABLE_PRODUCT_TYPE + "." + COLUMN_PRODUCT_TYPE_ID + " = " +
                TABLE_PRODUCT + "." + COLUMN_PRODUCT_TYPE_ID +
                " INNER JOIN " + TABLE_REPRESENTATIVE +
                " ON " + TABLE_REPRESENTATIVE + "." + COLUMN_PRODUCT_REPRESENTATIVE_ID + " = " +
                TABLE_PRODUCT + "." + COLUMN_PRODUCT_REPRESENTATIVE_ID +
                " INNER JOIN " + TABLE_INSTITUTION +
                " ON " + TABLE_INSTITUTION + "." + COLUMN_INSTITUTION_ID + " = " +
                TABLE_REPRESENTATIVE + "." + COLUMN_INSTITUTION_ID +
                " WHERE " + COLUMN_PRODUCT_ACTIVE_STATUS + " = ?";
        Boolean availableData = false;
        int loopCount = 0;

        if (arraySize > 0) {
            query = query + " AND ( " + COLUMN_PRODUCT_ID + " = ?";

            for (int i = 1; i < arraySize; i++) {
                query = query + " OR " + COLUMN_PRODUCT_ID + " = ?";
            }
        } else {
            query = query + " AND ( 1 = 0";
        }

        query = query + " )";

        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setBoolean(1, activeStatus);

        for (int i = 0; i < arraySize; i++) {
            preparedStatement.setInt(i + 2, productIds.get(i));
        }

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            loopCount++;
            System.out.println("----------" + loopCount + "----------");
            System.out.println("Product ID: " + "\t\t\t" + resultSet.getInt(COLUMN_PRODUCT_ID));
            System.out.println("Description: " + "\t\t\t" + resultSet.getString(COLUMN_PRODUCT_DESCRIPTION));
            //System.out.println("Product type ID: " + "\t\t" + resultSet.getInt(COLUMN_PRODUCT_TYPE_ID));
            System.out.println("Product type: " + "\t\t\t" + resultSet.getString(COLUMN_PRODUCT_TYPE_DESCRIPTION));
            //System.out.println("Representative ID: " + "\t\t" + resultSet.getInt(COLUMN_PRODUCT_REPRESENTATIVE_ID));
            System.out.println("Representative: " + "\t\t" + resultSet.getString(COLUMN_REPRESENTATIVE_FIRST_NAME) +
                    " " + resultSet.getString(COLUMN_REPRESENTATIVE_LAST_NAME));
            System.out.println("Email: " + "\t\t\t\t\t" + resultSet.getString(COLUMN_REPRESENTATIVE_EMAIL));
            System.out.println("Phone number: " + "\t\t\t" + resultSet.getString(COLUMN_REPRESENTATIVE_PHONE));
            System.out.println("Institution: " + "\t\t\t" + resultSet.getString(COLUMN_INSTITUTION_NAME));
            System.out.println("Institution's rating: " + "\t" + resultSet.getDouble(COLUMN_INSTITUTION_RATING));
            System.out.println("Amount: " + "\t\t\t\t" + resultSet.getDouble(COLUMN_PRODUCT_AMOUNT));
            System.out.println("Interest rate: " + "\t\t\t" + resultSet.getDouble(COLUMN_PRODUCT_INTEREST_RATE));
            System.out.println("Number of payments: " + "\t" + resultSet.getInt(COLUMN_PRODUCT_NUMBER_OF_PAYMENTS));
            System.out.println("Available from: " + "\t\t" + resultSet.getString(COLUMN_PRODUCT_AVAILABILITY_FROM));
            System.out.println("Available to: " + "\t\t\t" + resultSet.getString(COLUMN_PRODUCT_AVAILABILITY_TO));
            System.out.println("Status: " + "\t\t\t\t" + "Active");
            System.out.println("----------" + loopCount + "----------\n");
            availableData = true;
        }

        if (availableData) {
            System.out.println("\nNumber of records: " + loopCount + ".");
        } else {
            System.out.println("No data available.");
        }
    }

    public static void getAllProductTypeDescription() throws SQLException {
        PreparedStatement preparedStatement;
        String query = "SELECT * FROM " + TABLE_PRODUCT_TYPE;
        Boolean availableData = false;
        int loopCount = 0;

        preparedStatement = databaseConnection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            loopCount++;
            System.out.println(resultSet.getInt(COLUMN_PRODUCT_TYPE_ID) + ". " + resultSet.getString(COLUMN_PRODUCT_TYPE_DESCRIPTION));
            availableData = true;
        }

        if (availableData) {
            System.out.println("\nNumber of records: " + loopCount + ".");
        } else {
            System.out.println("No data available.");
        }
    }

    public static void updateProductTypeDescription(int productTypeId, String productTypeDescription) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "UPDATE " + TABLE_PRODUCT_TYPE +
                " SET " + COLUMN_PRODUCT_TYPE_DESCRIPTION + " = ?" +
                " WHERE " + COLUMN_PRODUCT_TYPE_ID + " = ?";

        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setString(1, productTypeDescription);
        preparedStatement.setInt(2, productTypeId);

        preparedStatement.executeUpdate();
    }

    /*
    public static void createProductType(String productTypeDescription) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO " + TABLE_PRODUCT_TYPE + " (" +
                COLUMN_PRODUCT_TYPE_DESCRIPTION + ") " +
                "VALUES (?)";

        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setString(1, productTypeDescription);

        preparedStatement.executeUpdate();
    }
    */
}
