package org.lendingtree.project;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public abstract class ConnectMSSQLServer {
    private static final String DATABASE_CONFIGURATION_FILEPATH = ".\\database_config.txt";
    private static final int DATABASE_URL = 0;
    private static final int DATABASE_USERNAME = 1;
    private static final int DATABASE_PASSWORD = 2;
    private static Connection databaseConnection;

    static {
        databaseConnection = getConnection();
    }

    public static Connection getConnection() {
        try {
            String databaseInfo[] = getDatabaseInfoFromFile();
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            databaseConnection = DriverManager.getConnection(databaseInfo[DATABASE_URL], databaseInfo[DATABASE_USERNAME], databaseInfo[DATABASE_PASSWORD]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseConnection;
    }

    private static String[] getDatabaseInfoFromFile(){
        BufferedReader bufferedReader;
        List<String> lines = new ArrayList<String>();
        String line;
        String linesFromFile[] = new String[3];
        try{
            int stringArrayPosition = 0;
            bufferedReader = new BufferedReader(new FileReader(DATABASE_CONFIGURATION_FILEPATH));
            while((line = bufferedReader.readLine()) != null) {
                lines.add(line);
                linesFromFile[stringArrayPosition] = line;
                stringArrayPosition++;
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return linesFromFile;
    }

}