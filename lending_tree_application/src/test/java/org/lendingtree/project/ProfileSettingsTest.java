package org.lendingtree.project;

import org.junit.Test;

import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.Connection;

public class ProfileSettingsTest {

    public static int CustomerId;
    public static String userchoice;
    public static String NewPassword;
    public static String Confirm_Password;
    public static String Newemail;

    private static Connection databaseConnection = ConnectMSSQLServer.getConnection();
    @Test
    public static void main(String args[]) {
        try {

            Scanner input = new Scanner(System.in);


            System.out.println("Please provide Customer Id :" );
            CustomerId = input.nextInt();


            int flag;
            do {
                System.out.println("press 1 to change password, press 2 to change email id");
                userchoice = input.next();
                flag = 0;
                switch (userchoice) {

                    case "1":
                        System.out.println("Please Enter New Password :");
                        NewPassword = input.next();
                        System.out.println("Please Re-Enter New Password :");
                        Confirm_Password = input.next();
                        if (NewPassword.equals(Confirm_Password)){
                            String query = "Update dbo.customer set customer_password=?" + " Where customer_id=" + CustomerId;

                            PreparedStatement statement = databaseConnection.prepareStatement(query);
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
                        Newemail = input.next();
                        String query1 = "Update dbo.customer set customer_email=?"+ " Where customer_id="+CustomerId;
                        PreparedStatement statement1 = databaseConnection.prepareStatement(query1);
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


