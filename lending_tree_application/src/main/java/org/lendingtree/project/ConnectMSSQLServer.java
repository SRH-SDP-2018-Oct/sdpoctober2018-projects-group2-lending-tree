package org.lendingtree.project;

import java.sql.*;

public class ConnectMSSQLServer {
    private static String databaseURL = "jdbc:sqlserver://localhost:1433;DatabaseName=lendingtree;allowMultiQueries=true";
    private static String databaseUserName = "sa";
    private static String databaseUserPassword = "admin123";
    private static Connection databaseConnection;

    static {
        databaseConnection = getConnection();
    }

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            databaseConnection = DriverManager.getConnection(databaseURL, databaseUserName, databaseUserPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseConnection;
    }

}