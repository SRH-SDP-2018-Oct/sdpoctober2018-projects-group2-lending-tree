package org.lendingtree.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTypeDatabase {

    private static final String TABLE_PRODUCT_TYPE = "product_type";
    private static final String COLUMN_PRODUCT_TYPE_ID = "product_type_id";
    private static final String COLUMN_PRODUCT_TYPE_DESCRIPTION = "product_type_description";

    private static Connection databaseConnection = ConnectMSSQLServer.getConnection();

    public static String getProductTypeDescription(int productTypeId) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "SELECT " + COLUMN_PRODUCT_TYPE_DESCRIPTION +
                " FROM " + TABLE_PRODUCT_TYPE + " WHERE " + COLUMN_PRODUCT_TYPE_ID + " = ?";

        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setInt(1, productTypeId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return "ID not valid.";
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

    public static void createProductType(String productTypeDescription) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO " + TABLE_PRODUCT_TYPE + " (" +
                COLUMN_PRODUCT_TYPE_DESCRIPTION + ") " +
                "VALUES (?)";

        preparedStatement = databaseConnection.prepareStatement(query);

        preparedStatement.setString(1, productTypeDescription);

        preparedStatement.executeUpdate();
    }

}
