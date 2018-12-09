package org.lendingtree.project;

import org.junit.Test;

public class ProfileTest {


    @Test
    public void getCustomerProfileTest() {
        int profileID = 12;
        try {
            ProfileDatabase.getCustomerProfile(profileID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getInstitutionProfileTest() {
        int profileID = 1;
        try {
            ProfileDatabase.getInstitutionProfile(profileID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
