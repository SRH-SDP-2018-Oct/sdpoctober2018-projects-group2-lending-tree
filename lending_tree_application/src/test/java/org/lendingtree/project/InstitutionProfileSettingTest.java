package org.lendingtree.project;

import java.util.Scanner;

public class InstitutionProfileSettingTest {



    public static void main(String args[]) {

        int institutionID ;

        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Please provide Institution Id :" );
            institutionID = input.nextInt();
            ProfileSettingsDatabase.getInstitutionSettings(institutionID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}