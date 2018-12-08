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

}
