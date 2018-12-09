package org.lendingtree.project;

import java.util.Scanner;

public class CustomerProfileSettingTest {



    public static void main(String args[]) {

        int customerID ;

        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Please provide Customer Id :" );
            customerID = input.nextInt();
            ProfileSettingDatabase.getCustomerSettings(customerID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}