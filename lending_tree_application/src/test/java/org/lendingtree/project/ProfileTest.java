package org.lendingtree.project;

import org.junit.Test;

import java.util.Scanner;

public class ProfileTest {


    @Test
    public void getCustomerProfileTest() {
        int profileID ;
        Scanner input = new Scanner(System.in);
        System.out.println("Please provide Customer Id :" );
        profileID = input.nextInt();
        try {
            ProfileDatabase.getCustomerProfile(profileID);      } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getInstitutionProfileTest() {
        int profileID ;
        Scanner input = new Scanner(System.in);
        System.out.println("Please provide Representative Id :" );
        profileID = input.nextInt();
        try {  ProfileDatabase.getRepresentativeProfile(profileID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
