package org.lendingtree.project;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;

public class ProfileSettingsTest {

    public static int CustomerId;
    public static String userchoice;
    public static String NewPassword;
    public static String NewPassword1;
    public static String Newemail;

    public static void main(String args[]) {
        try {

            Scanner Sc = new Scanner(System.in);
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=lendingtree;allowMultiQueries=true","sa","admin123");

            System.out.println("Please provide Customer Id :" );
            CustomerId = Sc.nextInt();


            int flag;
            do {
                System.out.println("press 1 to change password, press 2 to change email id");
                userchoice = Sc.next();
                flag = 0;
                switch (userchoice) {

                    case "1":
                        System.out.println("Please Enter New Password :");
                        NewPassword = Sc.next();
                        System.out.println("Please Re-Enter New Password :");
                        NewPassword1 = Sc.next();
                        if (NewPassword.equals(NewPassword1)){
                            String query = "Update dbo.customer set customer_password=?" + " Where customer_id=" + CustomerId;

                            PreparedStatement statement = con.prepareStatement(query);
                            statement.setString(1, NewPassword);
                            statement.execute();

                            System.out.println("Password Changed Successfully");
                            break;
                        }
                        else {
                            System.out.println("Wrong Entry");
                            break;
                        }
                    case "2":
                        System.out.println("Please Enter New email id :");
                        Newemail = Sc.next();
                        String query1 = "Update dbo.customer set customer_email=?"+ " Where customer_id="+CustomerId;
                        PreparedStatement statement1 = con.prepareStatement(query1);
                        statement1.setString(1,Newemail);
                        statement1.execute();
                        System.out.println("Email Changed Successfully");


                        break;
                    default:
                        System.out.println("Wrong Entry");
                        flag = 1;
                        break;
                }

            } while(flag > 0);


        }
        catch(Exception e)  {
            e.printStackTrace();
        }
    }

}


