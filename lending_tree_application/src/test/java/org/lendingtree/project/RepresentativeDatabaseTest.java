package org.lendingtree.project;

import org.junit.Assert;
import org.junit.Test;

public class RepresentativeDatabaseTest {
    @Test
    public void writeToDbTest(){
        String inputLastName = "Klinsmann";
        String inputFirstName = "Jürgen";
        String inputEmail = "jurgen.klinsmann@standard.com";
        String inputPhone = "+491621234567";
        String inputPassword = "jurgen_test";
        int inputInstitutionId = 1;
        int inputInstitutionDepartmentId = 2;

        Representative representative = new Representative();

        try{
            representative.setLastName(inputLastName);
            representative.setFirstName(inputFirstName);
            representative.setEmail(inputEmail);
            representative.setPhone(inputPhone);
            representative.setPassword(EncryptionTools.encryptPassword(inputPassword));
            RepresentativeDatabase.listInstitutions();
            representative.setInstitution(InstitutionDatabase.getInstitution(inputInstitutionId));
            RepresentativeDatabase.listInstitutionDepartments();
            representative.setInstitutionDepartmentId(inputInstitutionDepartmentId);
            representative.setInstitutionDepartment(RepresentativeDatabase.getInstitutionDepartment(representative.getInstitutionDepartmentId()));
            representative.setId(RepresentativeDatabase.insert(representative));

            System.out.println("Name: " + representative.getLastName() + ", " + representative.getFirstName());
            System.out.println("Email: " + representative.getEmail());
            System.out.println("Phone: " + representative.getPhone());
            System.out.println("Password: " + representative.getPassword());
            System.out.println("Institution: " + representative.getInstitution().getName());
            System.out.println("Institution Department: " + representative.getInstitutionDepartment());


        } catch (Exception e){
            e.printStackTrace();
        }

        Assert.assertEquals(inputLastName, representative.getLastName());
        Assert.assertEquals(inputFirstName, representative.getFirstName());
        Assert.assertEquals(inputEmail, representative.getEmail());
        Assert.assertEquals(inputPhone, representative.getPhone());
        Assert.assertEquals(EncryptionTools.encryptPassword(inputPassword), representative.getPassword());
        Assert.assertEquals("Standard Bank", representative.getInstitution().getName());
        Assert.assertEquals("PERSONAL", representative.getInstitutionDepartment());
    }

    @Test
    public void readFromDbTest(){
        String inputLastName = "Klinsmann";
        String inputFirstName = "Jürgen";
        String inputEmail = "jurgen.klinsmann@standard.com";
        String inputPhone = "+491621234567";
        String inputPassword = "jurgen_test";
        int inputInstitutionId = 1;
        int inputInstitutionDepartmentId = 2;

        Representative representative = new Representative();

        try{
            representative = RepresentativeDatabase.getRepresentative(inputEmail);

            System.out.println("Name: " + representative.getLastName() + ", " + representative.getFirstName());
            System.out.println("Email: " + representative.getEmail());
            System.out.println("Phone: " + representative.getPhone());
            System.out.println("Password: " + representative.getPassword());
            System.out.println("Institution: " + representative.getInstitution().getName());
            System.out.println("Institution Department: " + representative.getInstitutionDepartment());


        } catch (Exception e){
            e.printStackTrace();
        }

        Assert.assertEquals(inputLastName, representative.getLastName());
        Assert.assertEquals(inputFirstName, representative.getFirstName());
        Assert.assertEquals(inputEmail, representative.getEmail());
        Assert.assertEquals(inputPhone, representative.getPhone());
        Assert.assertEquals(EncryptionTools.encryptPassword(inputPassword), representative.getPassword());
        Assert.assertEquals("Standard Bank", representative.getInstitution().getName());
        Assert.assertEquals("PERSONAL", representative.getInstitutionDepartment());
    }
}
