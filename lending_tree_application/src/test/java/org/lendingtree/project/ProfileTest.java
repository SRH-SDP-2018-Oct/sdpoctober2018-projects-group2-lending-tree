package org.lendingtree.project;

import org.junit.Test;

import java.sql.*;

public class ProfileTest {

    @Test
    public void CustomerProfileTest() {

        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=lendingtree;allowMultiQueries=true";

        Connection connect_to_database;
        int customer_identity = 2;
        String query = "select * from dbo.customer Where customer_id =" + customer_identity;


        Statement statement;
        try {
            connect_to_database = DriverManager.getConnection(url,
                    "sa", "admin123");

            statement = connect_to_database.createStatement();

            ResultSet result = statement.executeQuery(query);
            /*ResultSetMetaData result_metadata = result.getMetaData();*/


            /*PrintColumnTypes.printColTypes(rsmd);*/

            if (result.next()) {
                String customer_id = result.getString(1);
                String customer_type_id = result.getString(2);
                String customer_last_name = result.getString(3);
                String customer_first_name = result.getString(4);
                String customer_address = result.getString(5);
                String customer_email = result.getString(6);
                String customer_phone = result.getString(7);
                String customer_current_expenses = result.getString(8);
                String customer_pay_slip = result.getString(9);
                String customer_tax_details = result.getString(10);
                String customer_identification_number = result.getString(11);
                String customer_rating = result.getString(12);
                String customer_password = result.getString(13);
                System.out.println("Customer id:" + customer_id);
                System.out.println("Customer Type id:" + customer_type_id);
                System.out.println("Last Name:" + customer_last_name);
                System.out.println("First Name   :" + customer_first_name);
                System.out.println("Address:" + customer_address);
                System.out.println("Email id:" + customer_email);
                System.out.println("Phone Number  :" + customer_phone);
                System.out.println("Current Expenses:" + customer_current_expenses);
                System.out.println("Pay Slip   :" + customer_pay_slip);
                System.out.println("Tax Details:" + customer_tax_details);
                System.out.println("Identification Number   :" + customer_identification_number);
                System.out.println("Customer Ratings:" + customer_rating);
                System.out.println("Password   :" + customer_password);
            } else {
                String message;
                message = "No Data Found!! User not Registered";
                System.out.println(message);
            }

        } catch (SQLException ex) {

            System.err.print("SQLException:");
            System.err.println(ex.getMessage());
        }
    }
        @Test
        public void LenderProfileTest() {
            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=lendingtree;allowMultiQueries=true";

            Connection connect_to_database;
            int lender_identity =1;
            String query = "select * from dbo.institution Where institution_id =" + lender_identity;


            Statement statement;
            try {
                connect_to_database = DriverManager.getConnection(url,
                        "sa", "admin123");

                statement = connect_to_database.createStatement();

                ResultSet result = statement.executeQuery(query);
                /*ResultSetMetaData result_metadata = result.getMetaData();*/


                /*PrintColumnTypes.printColTypes(rsmd);*/

                if (result.next()){
                    String institution_id=result.getString(1);
                    String institution_type_id=result.getString (2);
                    String institution_name=result.getString (3);
                    String institution_address=result.getString (4);
                    String institution_email=result.getString (5);
                    String institution_financial_status=result.getString (6);
                    String institution_rating=result.getString (7);
                    System.out.println ("Institution id:"+institution_id);
                    System.out.println ("Institution Type id:"+institution_type_id);
                    System.out.println ("Institution Name:"+institution_name);
                    System.out.println ("Institution Address:"+institution_address);
                    System.out.println ("Institution Email id:"+institution_email);
                    System.out.println ("Institution Financial Status  :"+institution_financial_status);
                    System.out.println ("institution Rating:"+institution_rating);
                }else {
                    String message;
                    message = "No Data Found!! User not Registered";
                    System.out.println(message);
                }

            } catch(SQLException ex) {

                System.err.print("SQLException:");
                System.err.println(ex.getMessage());
            }
        }

    }

