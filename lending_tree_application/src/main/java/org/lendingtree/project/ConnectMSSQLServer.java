package org.lendingtree.project;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ConnectMSSQLServer {
    private static final String DATABASE_CONFIGURATION_FILEPATH = ".\\database_config.txt";
    private static Connection databaseConnection;

    static {
        databaseConnection = getConnection();
    }

    public static Connection getConnection() {
        try {
            String databaseInfo[] = getDatabaseUrlFromFile();
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            databaseConnection = DriverManager.getConnection(databaseInfo[0], databaseInfo[1], databaseInfo[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseConnection;
    }

    private static String[] getDatabaseUrlFromFile(){
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