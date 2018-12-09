package org.lendingtree.project;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;

public class ProfileSettingsLenderTest {

    public static int LenderId;
    public static String Newemail;

    public static void main(String args[]) {
        try {

            Scanner Sc = new Scanner(System.in);
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=lendingtree;allowMultiQueries=true","sa","admin123");

            System.out.println("Please provide Institution Id :" );
            LenderId = Sc.nextInt();



            System.out.println("Please Enter New email id :");
            Newemail = Sc.next();
            String query1 = "Update dbo.institution set institution_email=?"+ " Where institution_id="+LenderId;
            PreparedStatement statement1 = con.prepareStatement(query1);
            statement1.setString(1,Newemail);
            statement1.execute();
            System.out.println("Email Changed Successfully");



        }
        catch(Exception e)  {
            e.printStackTrace();
        }
    }

}


