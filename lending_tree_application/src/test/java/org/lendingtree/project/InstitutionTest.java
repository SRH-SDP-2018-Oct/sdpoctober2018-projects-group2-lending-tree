package org.lendingtree.project;

import org.junit.Assert;
import org.junit.Test;

public class InstitutionTest {
    @Test
    public void setAndGetTest(){
        Double inputRating = 15.05;
        String inputFinancialStatus = "OK";
        String inputMail = "intitution_test@test.com";
        String inputName = "Standard Bank";
        String inputAddress = "Test Address 123, Heidelberg, Germany";
        int inputInstitutionTypeId = 1;

        Institution institution = new Institution();

        try {
            institution.setRating(inputRating);
            institution.setFinancialStatus(inputFinancialStatus);
            institution.setEmail(inputMail);
            institution.setAddress(inputAddress);
            institution.setName(inputName);
            institution.setInstitutionTypeId(inputInstitutionTypeId);
            InstitutionDatabase.listInstitutionTypes();
            institution.setInstitutionType(InstitutionDatabase.getInstitutionType(institution.getInstitutionTypeId()));

            System.out.println("Name: " + institution.getName());
            System.out.println("Address: " + institution.getAddress());
            System.out.println("Email: " + institution.getEmail());
            System.out.println("InstitutionType: " + InstitutionDatabase.getInstitutionType(institution.getInstitutionTypeId()));
            System.out.println("Rating: " + inputRating);
            System.out.println("Financial Status: " + inputFinancialStatus);

        } catch(Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals(inputRating, institution.getRating());
        Assert.assertEquals(inputFinancialStatus, institution.getFinancialStatus());
        Assert.assertEquals(inputMail, institution.getEmail());
        Assert.assertEquals(inputName, institution.getName());
        Assert.assertEquals(inputAddress, institution.getAddress());
        Assert.assertEquals(inputInstitutionTypeId, institution.getInstitutionTypeId());
    }
}
